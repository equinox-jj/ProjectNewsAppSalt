package com.projectnewsappsalt.data.di

import com.projectnewsappsalt.core.utils.constants.Constants
import com.projectnewsappsalt.data.datasource.remote.NewsRemoteDataSource
import com.projectnewsappsalt.data.datasource.remote.NewsRemoteDataSourceImpl
import com.projectnewsappsalt.data.datasource.remote.service.NewsApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideGsonConverter() }
    single { provideRetrofitClient(get(), get()) }
    single { provideNewsApiService(get()) }
    single<NewsRemoteDataSource> { NewsRemoteDataSourceImpl(get()) }
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

private fun provideGsonConverter(): GsonConverterFactory {
    return GsonConverterFactory.create()
}

private fun provideRetrofitClient(
    okHttpClient: OkHttpClient,
    gson: GsonConverterFactory,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gson)
        .build()
}

private fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
    return retrofit.create(NewsApiService::class.java)
}