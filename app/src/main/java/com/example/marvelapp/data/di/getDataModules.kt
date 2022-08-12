package com.example.marvelapp.data.di

import org.koin.core.module.Module
import org.koin.dsl.module

val apiModule = module {


}

val dataModules = listOf<Module>(
    apiModule
)