package dev.marcocattaneo.cryptogasprice.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.marcocattaneo.cryptogasprice.R
import dev.marcocattaneo.cryptogasprice.main.AlarmsViewModel
import dev.marcocattaneo.cryptogasprice.ui.widgets.*
import dev.marcocattaneo.cryptogasprice.utils.LiveDataResult
import dev.marcocattaneo.push.data.models.UIAlarm

@ExperimentalMaterialApi
@Composable
//TODO remove ViewModel as argument, replace with obj/callback
fun AlarmsScreen(alarmsViewModel: AlarmsViewModel) {
    val getAlarmsResult: LiveDataResult<List<UIAlarm>> by alarmsViewModel.alarmsLiveData
        .observeAsState(initial = LiveDataResult.Loading())
    var textValueState by remember { mutableStateOf("") }

    // TODO replace with bottom sheet
    Box(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            TextField(
                value = textValueState,
                onValueChange = { textValueState = it },
                label = { Text("Limit") }
            )
            Spacer(Modifier.weight(1f))
            PrimaryButton(
                icon = Icons.Default.Add,
                desc = stringResource(id = R.string.alarms_screen_add_label),
                onClick = {
                    alarmsViewModel.createAlarm(textValueState)
                    textValueState = ""
                },
            )
        }
        val bottomSheetState = rememberBottomSheetScaffoldState(bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Expanded
        ))
        AddAlarmBottomSheet(bottomSheetState = bottomSheetState) {
            
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            when (getAlarmsResult) {
                is LiveDataResult.Loading -> {
                    //TODO improve skeleton loading
                    item {
                        Loading()
                    }
                }
                is LiveDataResult.Error -> {
                    item {
                        ErrorState(message = (getAlarmsResult as LiveDataResult.Error<List<UIAlarm>>).error.message)
                    }
                }
                is LiveDataResult.Success -> {
                    (getAlarmsResult as LiveDataResult.Success<List<UIAlarm>>).data.forEach {
                        item {
                            AlarmItem(uiAlarm = it) {
                                alarmsViewModel.deleteAlarm(it)
                            }
                        }
                    }
                }
            }
        }
    }
}