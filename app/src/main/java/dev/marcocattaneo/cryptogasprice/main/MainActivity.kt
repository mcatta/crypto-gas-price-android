package dev.marcocattaneo.cryptogasprice.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.marcocattaneo.cryptogasprice.screens.AlarmsScreen
import dev.marcocattaneo.cryptogasprice.screens.DashboardScreen
import dev.marcocattaneo.cryptogasprice.ui.theme.CryptoGasPriceTheme
import dev.marcocattaneo.cryptogasprice.ui.widgets.BottomBar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    private val alarmsViewModel by viewModels<AlarmsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CryptoGasPriceTheme {
                val navController = rememberNavController()

                Scaffold(
                    content = {
                        NavHost(
                            navController = navController,
                            startDestination = ScreenRoutes.Dashboard.route
                        ) {
                            composable(ScreenRoutes.Dashboard.route) {
                                DashboardScreen(mainViewModel = mainViewModel)
                            }
                            composable(ScreenRoutes.Alarms.route) {
                                AlarmsScreen(alarmsViewModel = alarmsViewModel)
                            }
                        }
                    },

                    bottomBar = {
                        BottomBar(
                            navController,
                            listOf(ScreenRoutes.Dashboard, ScreenRoutes.Alarms)
                        )
                    }
                )
            }
        }
    }

}