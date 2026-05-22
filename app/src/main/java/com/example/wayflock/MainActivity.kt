package com.example.wayflock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.wayflock.ui.screens.home.HomeScreen
import com.example.wayflock.ui.theme.WayflockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WayflockTheme() {
                HomeScreen()
            }
        }
    }
}