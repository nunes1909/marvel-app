package com.example.marvelapp.util.activity

import android.app.Application
import com.example.marvelapp.data.di.dataModules
import com.example.marvelapp.domain.di.domainModules
import com.example.marvelapp.view.di.viewModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelApplication)
            modules(viewModules + domainModules + dataModules)
        }
    }
}