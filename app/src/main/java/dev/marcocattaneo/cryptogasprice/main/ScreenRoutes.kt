package dev.marcocattaneo.cryptogasprice.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import dev.marcocattaneo.cryptogasprice.R

sealed class ScreenRoutes(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Dashboard : ScreenRoutes("dashboard", R.string.dashboard_screen_route, Icons.Filled.Menu)
    object Alarms : ScreenRoutes("alarms", R.string.alarms_screen_route, Icons.Filled.Menu)
}