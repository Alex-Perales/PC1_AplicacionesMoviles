package com.perales.travelcompanion.screens

import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class TipoAlojamiento(val nombre: String, val factor: Double)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetPlannerScreen(navController: NavController) {
    val tiposAlojamiento = listOf(
        TipoAlojamiento("Económico", 0.8),
        TipoAlojamiento("Estándar", 1.0),
        TipoAlojamiento("Premium", 1.5)
    )

    var dias by remember { mutableStateOf("") }
    var presupuestoDiario by remember { mutableStateOf("") }
    var tipoSeleccionado by remember { mutableStateOf(tiposAlojamiento[1]) }
    var expanded by remember { mutableStateOf(false) }
    var resultado by remember { mutableStateOf<String?>(null) }
    var errorDias by remember { mutableStateOf("") }
    var errorPresupuesto by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Planificador de Presupuesto") },
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
            Text("Planifica tu presupuesto de viaje", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = dias,
                onValueChange = { dias = it; errorDias = ""; resultado = null },
                label = { Text("Cantidad de días") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = errorDias.isNotEmpty(),
                supportingText = { if (errorDias.isNotEmpty()) Text(errorDias, color = Color.Red) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = presupuestoDiario,
                onValueChange = { presupuestoDiario = it; errorPresupuesto = ""; resultado = null },
                label = { Text("Presupuesto diario (S/)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = errorPresupuesto.isNotEmpty(),
                supportingText = { if (errorPresupuesto.isNotEmpty()) Text(errorPresupuesto, color = Color.Red) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = "${tipoSeleccionado.nombre} (factor ${tipoSeleccionado.factor})",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo de alojamiento") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    tiposAlojamiento.forEach { tipo ->
                        DropdownMenuItem(
                            text = { Text("${tipo.nombre} (x${tipo.factor})") },
                            onClick = {
                                tipoSeleccionado = tipo
                                expanded = false
                                resultado = null
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    errorDias = ""
                    errorPresupuesto = ""
                    resultado = null

                    val diasVal = dias.toIntOrNull()
                    val presupuestoVal = presupuestoDiario.toDoubleOrNull()

                    var valido = true

                    when {
                        dias.isBlank() -> { errorDias = "Campo obligatorio"; valido = false }
                        diasVal == null -> { errorDias = "Ingrese un número entero"; valido = false }
                        diasVal <= 0 -> { errorDias = "Los días deben ser mayores a cero"; valido = false }
                    }
                    when {
                        presupuestoDiario.isBlank() -> { errorPresupuesto = "Campo obligatorio"; valido = false }
                        presupuestoVal == null -> { errorPresupuesto = "Ingrese un valor numérico"; valido = false }
                        presupuestoVal <= 0 -> { errorPresupuesto = "El presupuesto debe ser mayor a cero"; valido = false }
                    }

                    if (valido && diasVal != null && presupuestoVal != null) {
                        val total = diasVal * presupuestoVal * tipoSeleccionado.factor
                        val mensaje = when (tipoSeleccionado.nombre) {
                            "Económico" -> "Viaje económico: se aplica un descuento del 20% sobre el presupuesto base."
                            "Estándar" -> "Viaje estándar: se usa el presupuesto base sin ajustes."
                            "Premium" -> "Viaje premium: se aplica un incremento del 50% sobre el presupuesto base."
                            else -> ""
                        }
                        resultado = "Presupuesto total: S/ ${"%.2f".format(total)}\n\n$mensaje"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular presupuesto")
            }

            resultado?.let {
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                ) {
                    Text(
                        text = it,
                        modifier = Modifier.padding(16.dp),
                        color = Color(0xFF0D47A1),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
