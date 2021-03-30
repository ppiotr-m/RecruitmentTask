package com.example.recruitmenttask.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class FlightApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.d("flight", "Interceptor, request: $request")

        return chain.proceed(request)
    }
}