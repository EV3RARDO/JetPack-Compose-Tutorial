package com.example.myapplication.di

import com.example.myapplication.data.services.GithubService
import com.example.myapplication.domain.repository.GithubRepository
import com.example.myapplication.domain.repository.GithubRepositoryImpl
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
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesLoggingLevel(): HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY

    @Provides
    @Singleton
    fun providesLoggingInterceptor(level: HttpLoggingInterceptor.Level): Interceptor {
        return HttpLoggingInterceptor { message ->
            Timber.tag("OkHttp").v(message)
        }.apply { this.level = level }
    }

    @Provides
    @Singleton
    fun providesProductionMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        logging: Interceptor,
        moshi: Moshi,
    ): Retrofit.Builder {
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    @Provides
    @Singleton
    fun providesCmsApi(
        moshi: Moshi,
        logging: Interceptor,
    ): GithubService {
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GithubService::class.java)
    }

    @Provides
    @Singleton
    fun provideGitHubRepository(githubService: GithubService): GithubRepository {
        return GithubRepositoryImpl(githubService = githubService)
    }
}