package com.example.recruitmenttask.remote

import android.util.Log
import com.example.recruitmenttask.utils.ConstantValues.Companion.TAG
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset

class FlightApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response = chain.proceed(request)

        val responseBody = response.body()
        val source = responseBody!!.source()
        source.request(Long.MAX_VALUE)
        val buffer = source.buffer

        Log.d(TAG, "Interceptor, response:\n" + buffer.clone().readString(Charset.forName("UTF-8")).toString())

        return response
    }
}