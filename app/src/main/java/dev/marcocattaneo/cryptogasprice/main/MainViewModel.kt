package dev.marcocattaneo.cryptogasprice.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.marcocattaneo.cryptogasprice.error
import dev.marcocattaneo.cryptogasprice.loading
import dev.marcocattaneo.cryptogasprice.result
import dev.marcocattaneo.cryptogasprice.utils.LiveDataResult
import dev.marcocattaneo.gasprice.data.interactors.GetGasHistoriesUseCase
import dev.marcocattaneo.gasprice.data.interactors.GetLatestPriceUseCase
import dev.marcocattaneo.gasprice.data.models.UIGasPrice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLatestPriceUseCase: GetLatestPriceUseCase,
    private val gtGasHistoriesUseCase: GetGasHistoriesUseCase
) : ViewModel() {

    companion object {
        const val DELAY = 60000L
    }

    private val mLastPriceLiveData = MutableLiveData<LiveDataResult<UIGasPrice>>()
    val lastPriceLiveData: LiveData<LiveDataResult<UIGasPrice>>
        get() = mLastPriceLiveData

    private val mPriceHistoriesLiveData = MutableLiveData<LiveDataResult<List<UIGasPrice>>>()
    val priceHistoriesLiveData: LiveData<LiveDataResult<List<UIGasPrice>>>
        get() = mPriceHistoriesLiveData

    init {
        fetchHistories()
    }

    fun startFetchLatestPrice() {
        viewModelScope.launch {
            scheduleRepeatedly(DELAY) {
                //mLastPriceLiveData.loading()
                try {
                    mLastPriceLiveData.result(getLatestPriceUseCase.execute(null))
                } catch (e: Exception) {
                    mLastPriceLiveData.error(e)
                }
            }
        }
    }

    fun fetchHistories() {
        viewModelScope.launch {
            scheduleRepeatedly(DELAY) {
                //mPriceHistoriesLiveData.loading()
                try {
                    mPriceHistoriesLiveData.result(gtGasHistoriesUseCase.execute(null))
                } catch (e: Exception) {
                    mPriceHistoriesLiveData.error(e)
                }
            }
        }
    }

    private suspend fun scheduleRepeatedly(delayTimeMillis: Long, action: suspend CoroutineScope.() -> Unit) = coroutineScope {
        while (true) {
            launch { action() }
            delay(delayTimeMillis)
        }
    }

}