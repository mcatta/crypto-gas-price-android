package dev.marcocattaneo.cryptogasprice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.marcocattaneo.cryptogasprice.R
import dev.marcocattaneo.cryptogasprice.screens.AlarmsScreen
import dev.marcocattaneo.cryptogasprice.screens.DashboardScreen
import dev.marcocattaneo.cryptogasprice.splash.SplashFragment
import dev.marcocattaneo.cryptogasprice.ui.ScreenRoutes
import dev.marcocattaneo.cryptogasprice.ui.theme.CryptoGasPriceTheme
import dev.marcocattaneo.cryptogasprice.ui.widgets.BottomBar

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SplashFragment())
            .commit()
    }

}