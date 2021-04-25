package dev.marcocattaneo.cryptogasprice.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.marcocattaneo.cryptogasprice.di.CoroutineContextScope
import dev.marcocattaneo.cryptogasprice.error
import dev.marcocattaneo.cryptogasprice.loading
import dev.marcocattaneo.cryptogasprice.result
import dev.marcocattaneo.cryptogasprice.utils.LiveDataResult
import dev.marcocattaneo.gasprice.data.interactors.GetGasHistoriesUseCase
import dev.marcocattaneo.gasprice.data.interactors.GetLatestPriceUseCase
import dev.marcocattaneo.gasprice.data.models.UIGasPrice
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLatestPriceUseCase: GetLatestPriceUseCase,
    private val gtGasHistoriesUseCase: GetGasHistoriesUseCase,
    @CoroutineContextScope ioDispatcher: CoroutineContext
) : ViewModel() {

    companion object {
        const val DELAY = 60000L
    }

    private val coroutineScope = CoroutineScope(ioDispatcher + SupervisorJob())

    private val mLastPriceLiveData = MutableLiveData<LiveDataResult<UIGasPrice>>()
    val lastPriceLiveData: LiveData<LiveDataResult<UIGasPrice>>
        get() = mLastPriceLiveData

    private val mPriceHistoriesLiveData = MutableLiveData<LiveDataResult<List<UIGasPrice>>>()
    val priceHistoriesLiveData: LiveData<LiveDataResult<List<UIGasPrice>>>
        get() = mPriceHistoriesLiveData

    private val mTimerLiveData = MutableLiveData<Float>()
    val timerLiveData: LiveData<Float>
        get() = mTimerLiveData

    init {
        fetchHistories()
        fetchLatestPrice()
        startTimer()
    }

    fun fetchLatestPrice() {
        viewModelScope.launch {
            suspendFetchLatestPrice()
        }
    }

    fun fetchHistories() {
        viewModelScope.launch {
            suspendFetchHistories()
        }
    }

    private suspend fun suspendFetchLatestPrice() {
        mLastPriceLiveData.loading()
        try {
            mLastPriceLiveData.result(getLatestPriceUseCase.execute(null))
        } catch (e: Exception) {
            mLastPriceLiveData.error(e)
        }
    }

    private suspend fun suspendFetchHistories() {
        mPriceHistoriesLiveData.loading()
        try {
            mPriceHistoriesLiveData.result(gtGasHistoriesUseCase.execute(null))
        } catch (e: Exception) {
            mPriceHistoriesLiveData.error(e)
        }
    }

    fun startTimer() {
        coroutineScope.launch {
            while (true) {
                val interval = DELAY / 100L

                for (i in 0..DELAY step interval) {
                    delay(interval)
                    // tot : 100 = current: x
                    mTimerLiveData.postValue(((100f * i) / DELAY) / 100f)
                }
                viewModelScope.launch {
                    suspendFetchHistories()
                    suspendFetchLatestPrice()
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

}