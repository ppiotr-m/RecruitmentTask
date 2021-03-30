package com.example.recruitmenttask.remote

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlightServiceGenerator {

    companion object {
        private lateinit var retrofit: Retrofit

        fun getRetrofitInstance(): Retrofit {
            retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
            return retrofit
        }
    }
}