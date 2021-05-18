package dev.marcocattaneo.cryptogasprice.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.marcocattaneo.cryptogasprice.utils.LiveDataResult
import dev.marcocattaneo.push.data.interactors.CreateAlarmUseCase
import dev.marcocattaneo.push.data.interactors.DeleteAlarmUseCase
import dev.marcocattaneo.push.data.interactors.GetAlarmsUseCase
import dev.marcocattaneo.push.data.models.UIAlarm
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmsViewModel @Inject constructor(
    private val getAlarmsUseCase: GetAlarmsUseCase,
    private val createAlarmUseCase: CreateAlarmUseCase,
    private val deleteAlarmUseCase: DeleteAlarmUseCase
) : ViewModel() {

    private val mAlarmsLiveData = MutableLiveData<LiveDataResult<List<UIAlarm>>>()
    val alarmsLiveData: LiveData<LiveDataResult<List<UIAlarm>>>
        get() = mAlarmsLiveData

    init {
        fetchAlarms()
    }

    fun fetchAlarms() {
        viewModelScope.launch {
            mAlarmsLiveData.value = LiveDataResult.Loading(true)
            try {
                mAlarmsLiveData.value = LiveDataResult.Success(getAlarmsUseCase.execute(null))
            } catch (e: Exception) {
                mAlarmsLiveData.value = LiveDataResult.Error(e)
            }
        }
    }

    fun createAlarm(value: String) {
        viewModelScope.launch {
            mAlarmsLiveData.value = LiveDataResult.Loading(true)
            try {
                createAlarmUseCase.execute(value.toDouble())
                // Then fetch
                mAlarmsLiveData.value = LiveDataResult.Success(getAlarmsUseCase.execute(null))
            } catch (e: Exception) {
                mAlarmsLiveData.value = LiveDataResult.Error(e)
            }
        }
    }

    fun deleteAlarm(id: String) {
        viewModelScope.launch {
            mAlarmsLiveData.value = LiveDataResult.Loading(true)
            try {
                deleteAlarmUseCase.execute(id)
                // Then fetch
                mAlarmsLiveData.value = LiveDataResult.Success(getAlarmsUseCase.execute(null))
            } catch (e: Exception) {
                mAlarmsLiveData.value = LiveDataResult.Error(e)
            }
        }
    }

}