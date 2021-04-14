package dev.marcocattaneo.cryptogasprice.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.marcocattaneo.cryptogasprice.ui.theme.CryptoGasPriceTheme

@Composable
fun ErrorState(message: String?) {
    Card(backgroundColor = MaterialTheme.colors.error, modifier = Modifier.padding(16.dp)) {
        Text(
            text = message ?: "Generic Error",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            color = MaterialTheme.colors.onError
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LightErrorStatePreview() {
    CryptoGasPriceTheme(darkTheme = false) {
        ErrorState("Error")
    }
}


@Preview(showBackground = true)
@Composable
fun DarkErrorStatePreview() {
    CryptoGasPriceTheme(darkTheme = true) {
        ErrorState("Error")
    }
}