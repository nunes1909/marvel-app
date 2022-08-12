package com.example.marvelapp.view.di

import org.koin.core.module.Module
import org.koin.dsl.module

val uiModules = module {

}

val viewModules = listOf<Module>(
    uiModules
)