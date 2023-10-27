package com.github.stefaniniteste.domain.usecase.get_images

import com.github.stefaniniteste.commom.Resources
import com.github.stefaniniteste.commom.Response
import com.github.stefaniniteste.data.remote.Gallery
import com.github.stefaniniteste.di.Api
import com.github.stefaniniteste.domain.data.FakeImageRepository
import com.google.gson.GsonBuilder
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream


class GetImageListUseCaseTest {

    private lateinit var getImageListUseCase: GetImageListUseCase
    private lateinit var api: Api
    private lateinit var fakeImageRepository: FakeImageRepository
    private lateinit var mockWebServer: MockWebServer
    private lateinit var response: Response<Gallery>
    private val gallery = mockk<Gallery>(relaxed = true)

    private val client = OkHttpClient.Builder().build()

    @Before
    fun setUp() {
        response = Response(data = listOf(gallery))

        val gson = GsonBuilder()
            .setLenient()
            .create()

        mockWebServer = MockWebServer()

        api = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .client(client).addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(Api::class.java)

        fakeImageRepository = FakeImageRepository(api)
        getImageListUseCase = GetImageListUseCase(repository = fakeImageRepository)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `check if 400 response result in an error state`() = runTest {
        val mockResponse = MockResponse()
            .setBody("The client messed this up")
            .setResponseCode(400)
        mockWebServer.enqueue(mockResponse)

        val flow = getImageListUseCase()
        val emits = flow.toList()

        assertTrue(emits[0] is Resources.Loading)
        assertTrue(emits[1] is Resources.Error)
        assertTrue((emits[1] as Resources.Error).message == "HTTP 400 Client Error")

    }

}