package com.github.stefaniniteste.di

import com.github.stefaniniteste.commom.Constants.BASE_URL
import com.github.stefaniniteste.commom.Constants.CLIENT_ID
import com.github.stefaniniteste.data.repository.RepositoryImpl
import com.github.stefaniniteste.domain.repository.Repository
import com.github.stefaniniteste.domain.usecase.get_images.GetImageListUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object Module {

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .build()

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesInterceptor() = Interceptor {
        val request = it.request().newBuilder()
            .addHeader("Authorization", "Client-ID $CLIENT_ID")
            .build()
        it.proceed(request)
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Singleton
    @Provides
    fun provideRepository(api: Api): Repository = RepositoryImpl(api)

    @Singleton
    @Provides
    fun provideGetImageListUseCase(repository: Repository): GetImageListUseCase =
        GetImageListUseCase(repository)

}