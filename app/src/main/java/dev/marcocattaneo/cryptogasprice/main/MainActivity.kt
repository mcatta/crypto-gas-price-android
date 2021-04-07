package dev.marcocattaneo.cryptogasprice.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import dagger.hilt.android.AndroidEntryPoint
import dev.marcocattaneo.cryptogasprice.ui.theme.CryptoGasPriceTheme
import dev.marcocattaneo.cryptogasprice.ui.widgets.PriceCard
import dev.marcocattaneo.cryptogasprice.utils.LiveDataResult
import dev.marcocattaneo.gasprice.data.models.UIGasPrice

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoGasPriceTheme {
                GasFeeList()
            }
        }

        mainViewModel.fetchLatestPrice()
    }

    @Composable
    fun GasFeeList() {
        val gasPricesResult: LiveDataResult<UIGasPrice> by mainViewModel.lastPriceLiveData
            .observeAsState(initial = LiveDataResult.Loading())

        Column {
            when (gasPricesResult) {
                is LiveDataResult.Loading -> {

                }
                is LiveDataResult.Error -> {

                }
                is LiveDataResult.Success -> {
                    val res = (gasPricesResult as LiveDataResult.Success<UIGasPrice>)
                    PriceCard(price = res.data.slow)
                    PriceCard(price = res.data.fast)
                    PriceCard(price = res.data.fastest)
                }
            }
        }
    }
}