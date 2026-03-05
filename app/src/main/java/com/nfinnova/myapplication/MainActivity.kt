package com.nfinnova.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.nfinnova.core_ui.composable.AppWrapper
import com.nfinnova.myapplication.ui.theme.composable.NavHostProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(
                content = {
                    AppWrapper {
                        NavHostProvider()
                    }
                }
            )
        }
    }
}

