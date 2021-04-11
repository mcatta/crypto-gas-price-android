package dev.marcocattaneo.cryptogasprice.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import dagger.hilt.android.AndroidEntryPoint
import dev.marcocattaneo.cryptogasprice.R
import dev.marcocattaneo.cryptogasprice.ui.theme.CryptoGasPriceTheme
import dev.marcocattaneo.cryptogasprice.ui.widgets.*
import dev.marcocattaneo.cryptogasprice.utils.LiveDataResult
import dev.marcocattaneo.gasprice.data.models.UIGasPrice

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CryptoGasPriceTheme {
                Scaffold(
                    content = {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            item { GasFeeChart() }
                            item { GasFeeList() }
                        }
                    }
                )
            }
        }

        mainViewModel.startFetchLatestPrice()
    }

    @Composable
    fun GasFeeChart() {
        val gasHistoriesResult: LiveDataResult<List<UIGasPrice>> by mainViewModel.priceHistoriesLiveData
            .observeAsState(initial = LiveDataResult.Loading())

        Column(modifier = Modifier.fillMaxWidth()) {
            when (val res = gasHistoriesResult) {
                is LiveDataResult.Loading -> {

                }
                is LiveDataResult.Error -> {
                    ErrorState(res.error.message)
                }
                is LiveDataResult.Success -> {
                    ChartWidget(modifier = Modifier, listOf(
                        ChartDataSet(
                            lineColor = R.color.design_default_color_secondary,
                            res.data.map { it.slow.price.toFloat() }
                        ),
                        ChartDataSet(
                            lineColor = R.color.design_default_color_secondary,
                            res.data.map { it.fast.price.toFloat() }
                        ),
                        ChartDataSet(
                            lineColor = R.color.design_default_color_secondary,
                            res.data.map { it.fastest.price.toFloat() }
                        )
                    ))
                }
            }
        }
    }

    @Composable
    fun GasFeeList() {
        val gasPricesResult: LiveDataResult<UIGasPrice> by mainViewModel.lastPriceLiveData
            .observeAsState(initial = LiveDataResult.Loading())

        Column(modifier = Modifier.padding(top = 8.dp)) {
            when (val res = gasPricesResult) {
                is LiveDataResult.Loading -> {

                }
                is LiveDataResult.Error -> {
                    ErrorState(res.error.message)
                }
                is LiveDataResult.Success -> {
                    PriceCard(
                        price = res.data.slow,
                        stringResource(id = R.string.price_card_speed_slow)
                    )
                    PriceCard(
                        price = res.data.fast,
                        stringResource(id = R.string.price_card_speed_fast)
                    )
                    PriceCard(
                        price = res.data.fastest,
                        stringResource(id = R.string.price_card_speed_fastest)
                    )
                }
            }
        }
    }
}