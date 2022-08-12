package com.example.marvelapp.data.di

import com.example.marvelapp.view.util.constants.Constants.BASE_URL
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {


}

val dataModules = listOf<Module>(
    apiModule
)