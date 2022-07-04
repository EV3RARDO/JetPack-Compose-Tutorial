package com.example.myapplication

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.RepositoriesPagingSource
import com.example.myapplication.data.RestaurantRepositoryImpl
import com.example.myapplication.data.local.RestaurantsDao
import com.example.myapplication.data.local.RestaurantsDb
import com.example.myapplication.data.services.RepositoriesApiService
import com.example.myapplication.data.services.RestaurantsApiService
import com.example.myapplication.domain.repository.RestaurantRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
    fun providesRepositoryApiService(
        logging: Interceptor,
    ): RepositoriesApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com/search/")
            .client(client)
            .build()
            .create(RepositoriesApiService::class.java)
    }

/*    @Provides
    @Singleton
    fun provideGitHubRepository(githubService: GithubService): GithubRepository {
        return GithubRepositoryImpl(githubService = githubService)
    }*/


    @Provides
    @Singleton
    fun provideRestaurantsService(logging: Interceptor): RestaurantsApiService {

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl("https://restaurantsjetpackcomposedemo-default-rtdb.firebaseio.com/")
            .build()
            .create(RestaurantsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRestaurantsRepository(
        restaurantsApiService: RestaurantsApiService,
        restaurantsDao: RestaurantsDao
    ): RestaurantRepository {
        return RestaurantRepositoryImpl(restaurantsApiService, restaurantsDao)
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): RestaurantsDb {
        return Room.databaseBuilder(
            context,
            RestaurantsDb::class.java,
            "restaurants_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    @Singleton
    fun provideRestaurantsDao(restaurantsDb: RestaurantsDb): RestaurantsDao {
        return restaurantsDb.dao
    }

    @Provides
    @Singleton
    fun provideRepositoriesPaginSource(
        repositoriesApiService: RepositoriesApiService
    ): RepositoriesPagingSource {
        return RepositoriesPagingSource(repositoriesApiService = repositoriesApiService)
    }
}