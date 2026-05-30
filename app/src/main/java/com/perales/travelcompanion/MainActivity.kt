package com.perales.travelcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.perales.travelcompanion.navigation.AppNavGraph
import com.perales.travelcompanion.ui.theme.TravelCompanionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelCompanionTheme {
                AppNavGraph()
            }
        }
    }
}
