package com.example.poccoroutines.di

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    val gson = GsonBuilder().create()

    val gsonFactory = GsonConverterFactory.create(gson)

    single {
        OkHttpClient.Builder()
            .build()
    }

    single<Retrofit> {
        buildRetrofit(get(), gsonFactory, "https://api.github.com/")
        /**
         * url do proxyman
         * buildRetrofit(get(), gsonFactory, "http://609a908e0f5a13001721b74e.mockapi.io/")
         */
    }
}

private fun buildRetrofit(client: OkHttpClient, gsonFactory: GsonConverterFactory, url: String) =
    Retrofit.Builder()
        .client(client)
        .baseUrl(url)
        .addConverterFactory(gsonFactory)
        .build()