package dev.marcocattaneo.cryptogasprice.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.marcocattaneo.cryptogasprice.screens.AlarmsScreen
import dev.marcocattaneo.cryptogasprice.screens.DashboardScreen
import dev.marcocattaneo.cryptogasprice.ui.ScreenRoutes
import dev.marcocattaneo.cryptogasprice.ui.theme.CryptoGasPriceTheme
import dev.marcocattaneo.cryptogasprice.ui.widgets.BottomBar

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainFragment : Fragment() {

    private val mainViewModel by activityViewModels<MainViewModel>()

    private val alarmsViewModel by activityViewModels<AlarmsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
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
}