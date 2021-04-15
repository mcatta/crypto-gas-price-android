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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import dev.marcocattaneo.cryptogasprice.R
import dev.marcocattaneo.cryptogasprice.ui.theme.*
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
    }

    @Composable
    fun GasFeeChart() {
        val gasHistoriesResult: LiveDataResult<List<UIGasPrice>> by mainViewModel.priceHistoriesLiveData
            .observeAsState(initial = LiveDataResult.Loading())

        Column(modifier = Modifier.fillMaxWidth()) {
            when (val res = gasHistoriesResult) {
                is LiveDataResult.Loading,
                is LiveDataResult.Success -> {
                    val data = if (res is LiveDataResult.Success) res.data else listOf()
                    ChartWidget(modifier = Modifier, listOf(
                        ChartDataSet(
                            lineColor = R.color.gas_price_line_color_slow,
                            data.map { Pair(it.slow.price.toFloat(), it.lastUpdate) }
                        ),
                        ChartDataSet(
                            lineColor = R.color.gas_price_line_color_fast,
                            data.map { Pair(it.fast.price.toFloat(), it.lastUpdate) }
                        ),
                        ChartDataSet(
                            lineColor = R.color.gas_price_line_color_fastest,
                            data.map { Pair(it.fastest.price.toFloat(), it.lastUpdate) }
                        )
                    ))
                }
                is LiveDataResult.Error -> {
                    ErrorState(res.error.message)
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
                is LiveDataResult.Loading,
                is LiveDataResult.Success -> {
                    val slowPrice =
                        if (res is LiveDataResult.Success) res.data.slow else UIGasPrice.Item(0, 0)
                    val fastPrice =
                        if (res is LiveDataResult.Success) res.data.fast else UIGasPrice.Item(0, 0)
                    val fastestPrice =
                        if (res is LiveDataResult.Success) res.data.fastest else UIGasPrice.Item(
                            0,
                            0
                        )
                    PriceCard(
                        price = slowPrice,
                        subtitle = stringResource(id = R.string.price_card_speed_fastest),
                        badgeColor = colorResource(id = R.color.gas_price_line_color_fastest)
                    )
                    PriceCard(
                        price = fastPrice,
                        subtitle = stringResource(id = R.string.price_card_speed_fast),
                        badgeColor = colorResource(id = R.color.gas_price_line_color_fast)
                    )
                    PriceCard(
                        price = fastestPrice,
                        subtitle = stringResource(id = R.string.price_card_speed_slow),
                        badgeColor = colorResource(id = R.color.gas_price_line_color_slow)
                    )
                }
                is LiveDataResult.Error -> {
                    ErrorState(res.error.message)
                }
            }
        }
    }
}