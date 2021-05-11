package dev.marcocattaneo.cryptogasprice.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.marcocattaneo.cryptogasprice.main.AlarmsViewModel

@Composable
fun AlarmsScreen(alarmsViewModel: AlarmsViewModel) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        //TODO show alarms list
    }
}