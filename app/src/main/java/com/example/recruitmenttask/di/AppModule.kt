package com.example.recruitmenttask.di

import com.example.recruitmenttask.remote.FlightAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
class AppModule {

    @Provides
    fun getFlightApi(retrofit: Retrofit): FlightAPI =
        retrofit.create(FlightAPI::class.java)
}