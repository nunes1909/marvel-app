package com.example.marvelapp.view.util.activity

import android.app.Application
import com.example.marvelapp.view.di.viewModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelApplication)
            modules(viewModules)
        }
    }
}