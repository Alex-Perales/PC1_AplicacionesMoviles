package com.perales.travelcompanion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.perales.travelcompanion.screens.BudgetPlannerScreen
import com.perales.travelcompanion.screens.DestinationCatalogScreen
import com.perales.travelcompanion.screens.LocationPermissionScreen
import com.perales.travelcompanion.screens.LuggageCalculatorScreen
import com.perales.travelcompanion.screens.MainMenuScreen

object Routes {
    const val MAIN_MENU = "main_menu"
    const val LUGGAGE_CALCULATOR = "luggage_calculator"
    const val BUDGET_PLANNER = "budget_planner"
    const val DESTINATION_CATALOG = "destination_catalog"
    const val LOCATION_PERMISSION = "location_permission"
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.MAIN_MENU) {
        composable(Routes.MAIN_MENU) {
            MainMenuScreen(navController = navController)
        }
        composable(Routes.LUGGAGE_CALCULATOR) {
            LuggageCalculatorScreen(navController = navController)
        }
        composable(Routes.BUDGET_PLANNER) {
            BudgetPlannerScreen(navController = navController)
        }
        composable(Routes.DESTINATION_CATALOG) {
            DestinationCatalogScreen(navController = navController)
        }
        composable(Routes.LOCATION_PERMISSION) {
            LocationPermissionScreen(navController = navController)
        }
    }
}
