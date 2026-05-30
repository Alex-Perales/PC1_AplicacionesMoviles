package com.perales.travelcompanion.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.perales.travelcompanion.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Travel Companion App") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Menú Principal",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { navController.navigate(Routes.LUGGAGE_CALCULATOR) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("🧳 Calculadora de Equipaje")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Routes.BUDGET_PLANNER) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("💰 Planificador de Presupuesto")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Routes.DESTINATION_CATALOG) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("🌍 Catálogo de Destinos")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Routes.LOCATION_PERMISSION) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("📍 Permiso de Ubicación")
            }
        }
    }
}
