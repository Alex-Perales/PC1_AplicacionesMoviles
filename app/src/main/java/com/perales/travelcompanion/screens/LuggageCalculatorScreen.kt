package com.perales.travelcompanion.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LuggageCalculatorScreen(navController: NavController) {
    var peso by remember { mutableStateOf("") }
    var tipoVuelo by remember { mutableStateOf("Nacional") }
    var resultado by remember { mutableStateOf<String?>(null) }
    var excedido by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf("") }

    val tiposVuelo = listOf("Nacional", "Internacional")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculadora de Equipaje") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Ingrese los datos del equipaje", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = peso,
                onValueChange = {
                    peso = it
                    error = ""
                    resultado = null
                },
                label = { Text("Peso de la maleta (kg)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = error.isNotEmpty(),
                supportingText = { if (error.isNotEmpty()) Text(error, color = Color.Red) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Tipo de vuelo:", fontWeight = FontWeight.Medium)

            tiposVuelo.forEach { tipo ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = tipoVuelo == tipo,
                        onClick = {
                            tipoVuelo = tipo
                            resultado = null
                        }
                    )
                    val limite = if (tipo == "Nacional") 23 else 32
                    Text("$tipo (máx. $limite kg)")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    error = ""
                    resultado = null
                    when {
                        peso.isBlank() -> error = "El campo es obligatorio"
                        peso.toDoubleOrNull() == null -> error = "Ingrese un valor numérico"
                        peso.toDouble() <= 0 -> error = "El peso debe ser mayor a cero"
                        else -> {
                            val pesoVal = peso.toDouble()
                            val limite = if (tipoVuelo == "Nacional") 23.0 else 32.0
                            if (pesoVal <= limite) {
                                excedido = false
                                resultado = "✅ Cumple el límite permitido ($limite kg para vuelo $tipoVuelo)"
                            } else {
                                excedido = true
                                val exceso = pesoVal - limite
                                resultado = "❌ Excede el límite de $limite kg\nExceso: ${"%.2f".format(exceso)} kg"
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular")
            }

            resultado?.let {
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (excedido) Color(0xFFFFEBEE) else Color(0xFFE8F5E9)
                    )
                ) {
                    Text(
                        text = it,
                        modifier = Modifier.padding(16.dp),
                        color = if (excedido) Color(0xFFB71C1C) else Color(0xFF1B5E20),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
