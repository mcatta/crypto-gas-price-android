package dev.marcocattaneo.cryptogasprice.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.marcocattaneo.cryptogasprice.ui.theme.CryptoGasPriceTheme
import dev.marcocattaneo.gasprice.data.models.UIGasPrice


@Composable
fun PriceCard(price: UIGasPrice.Item) {
    Card(
        elevation = 2.dp,
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = 8.dp,
            bottom = 8.dp
        )
    ) {
        Text(
            text = "${price.price} Gwei",
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LightPriceCardPreview() {
    CryptoGasPriceTheme(darkTheme = false) {
        PriceCard(UIGasPrice.Item(300, 23.3))
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPriceCardPreview() {
    CryptoGasPriceTheme(darkTheme = true) {
        PriceCard(UIGasPrice.Item(300, 23.3))
    }
}
