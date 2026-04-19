package com.flopez.mymenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.flopez.core.presentation.theme.VerdantPantryTheme
import com.flopez.feature.authentication.presentation.login.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VerdantPantryTheme {
                Surface {
                    LoginScreen()
                }
            }
        }
    }
}
