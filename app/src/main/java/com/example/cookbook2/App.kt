package com.example.cookbook2

import android.app.Application
import android.content.Context
import com.example.cookbook2.di.appModule
import com.example.cookbook2.di.categoriesModule
import com.example.cookbook2.di.recipesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        _appContext = this
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(appModule + recipesModule + categoriesModule)
        }

    }

    companion object{
        private lateinit var _appContext: Context
        val appContext: Context
            get() = _appContext
    }
}