package com.example.recruitmenttask.remote

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlightServiceGenerator {

    companion object {
        private lateinit var retrofit: Retrofit

        fun getRetrofitInstance(): Retrofit {
            retrofit = Retrofit.Builder()
                .baseUrl("https://tripstest.ryanair.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(buildOkHttpClient())
                .build()
            return retrofit
        }

        fun buildOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(FlightApiInterceptor())
                .build()
        }
    }
}