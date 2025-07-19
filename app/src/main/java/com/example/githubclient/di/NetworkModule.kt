package com.example.githubclient.di

import com.example.githubclient.BuildConfig
import com.example.githubclient.data.remote.GithubApi
import com.example.githubclient.data.repository.GithubRepository
import com.example.githubclient.data.repository.GithubRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val authInterceptor = Interceptor { chain ->
            val newReq = chain.request().newBuilder()
                .addHeader("Authorization", "token ${BuildConfig.GITHUB_TOKEN}")
                .build()
            chain.proceed(newReq)
        }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit): GithubApi =
        retrofit.create(GithubApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: GithubApi): GithubRepository =
        GithubRepositoryImpl(api)
}
