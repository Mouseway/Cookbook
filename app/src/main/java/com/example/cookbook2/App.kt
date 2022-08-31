package com.example.cookbook2

import android.app.Application
import com.example.cookbook2.di.appModule
import com.example.cookbook2.di.recipesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(appModule + recipesModule)
        }
    }
}