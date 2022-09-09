package com.example.cookbook2.di

import androidx.room.Room
import com.example.cookbook2.App
import com.example.cookbook2.data.room.CookbookDatabase
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            App.appContext,
            CookbookDatabase::class.java,
            "my_db"
        )
            .createFromAsset("database/db.db")
            .build()
    }
}