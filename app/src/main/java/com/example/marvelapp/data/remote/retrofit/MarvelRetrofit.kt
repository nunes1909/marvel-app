package com.example.marvelapp.data.remote.retrofit

import com.example.marvelapp.data.remote.service.ServiceApi
import com.example.marvelapp.view.util.constants.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarvelRetrofit {

    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getApiService(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }
}