package com.example.cookbook2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.cookbook2.data.repositories.RecipesRepository
import com.example.cookbook2.navigation.Navigation
import com.example.cookbook2.ui.theme.Cookbook2Theme
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.activityScope


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Cookbook2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    Navigation()
                }
            }
        }
    }
}