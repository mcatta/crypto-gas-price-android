package dev.marcocattaneo.cryptogasprice.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.marcocattaneo.cryptogasprice.R
import dev.marcocattaneo.cryptogasprice.ui.theme.CryptoGasPriceTheme
import dev.marcocattaneo.cryptogasprice.utils.CommonUtils
import dev.marcocattaneo.gasprice.data.models.UIGasPrice


@Composable
fun PriceCard(price: UIGasPrice.Item, subtitle: String) {
    Card(
        elevation = 0.dp,
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = 8.dp,
            bottom = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${price.price} ${stringResource(id = R.string.price_card_currency_gwey)}",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primary
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = CommonUtils.convertSecondsToMinutes(price.speedSeconds).let { time ->
                        if (time.first > 0)
                            "ETA: ${time.first} ${stringResource(id = R.string.time_format_minutes)}"
                        else
                            "ETA: ${time.second} ${stringResource(id = R.string.time_format_seconds)}"
                    }
                )
            }
            Text(
                text = subtitle
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun LightPriceCardPreview() {
    CryptoGasPriceTheme(darkTheme = false) {
        PriceCard(UIGasPrice.Item(300, 60), "Fast")
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPriceCardPreview() {
    CryptoGasPriceTheme(darkTheme = true) {
        PriceCard(UIGasPrice.Item(300, 600), "Slow")
    }
}
