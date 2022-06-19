package com.example.movieclone.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getRetrofitInstance(okHttpClient: OkHttpClient,gson:Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun getGson() : Gson{
        return GsonBuilder().serializeNulls().setLenient().create()
    }

    @Provides
    @Singleton
    fun getInterceptor() : Interceptor{
        return Interceptor {
            val request = it.request()
            val origHttpUrl = request.url

            val url = origHttpUrl.newBuilder()
                .addQueryParameter("api_key","")
                .build()

            val requestBuilder = request.newBuilder().url(url)
            val mRequest = requestBuilder.build()
            it.proceed(mRequest)
        }
    }

    @Provides
    @Singleton
    fun getHttpClient(interceptor: Interceptor) :OkHttpClient{
       val httpBuilder = OkHttpClient.Builder()
           .addInterceptor(interceptor)
           .readTimeout(30, TimeUnit.SECONDS)
           .readTimeout(30,TimeUnit.SECONDS)

        return httpBuilder.build()
    }

    @Provides
    @Singleton
    fun  getMoviesService(retrofit: Retrofit) : MovieRepo{
        return retrofit.create(MovieRepo ::class.java)
    }
}