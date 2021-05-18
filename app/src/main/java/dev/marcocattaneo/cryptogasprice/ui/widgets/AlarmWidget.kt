package dev.marcocattaneo.cryptogasprice.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.marcocattaneo.cryptogasprice.R
import dev.marcocattaneo.cryptogasprice.ui.theme.CryptoGasPriceTheme
import dev.marcocattaneo.push.data.models.UIAlarm

@Composable
fun AlarmItem(uiAlarm: UIAlarm, onClickDelete: (String) -> Unit) {
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
                Column {
                    Text(
                        text = "${uiAlarm.limit} gwei",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primary
                    )
                    Text(
                        text = if (uiAlarm.enabled) {
                            stringResource(id = R.string.alarm_item_enabled)
                        } else {
                            stringResource(id = R.string.alarm_item_disabled)
                        },
                        style = MaterialTheme.typography.body2
                    )
                }
                Spacer(Modifier.weight(1f))
                PrimaryButton(label = stringResource(id = R.string.alarm_item_delete_label)) {
                    onClickDelete.invoke(uiAlarm.id)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LightAlarmPreview() {
    CryptoGasPriceTheme(darkTheme = false) {
        AlarmItem(uiAlarm = UIAlarm("id", 42.0, true)) {}
    }
}

@Preview(showBackground = true)
@Composable
fun DarkAlarmPreview() {
    CryptoGasPriceTheme(darkTheme = true) {
        AlarmItem(uiAlarm = UIAlarm("id", 42.0, true)) {}
    }
}

@ExperimentalMaterialApi
@Composable
fun AddAlarmBottomSheet(bottomSheetState: BottomSheetScaffoldState, onClickAdd: (String) -> Unit) {
    var textValueState by remember { mutableStateOf("") }

    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetContent = {
            Row(
                Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = textValueState,
                    onValueChange = { textValueState = it },
                    label = { Text("Trigger below this limit") }
                )
                PrimaryButton(
                    label = stringResource(id = R.string.alarms_screen_add_label),
                    onClick = {
                        onClickAdd.invoke(textValueState)
                    }
                )
            }
        }
    ) {

    }
}
