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
import dev.marcocattaneo.gasprice.data.interactors.GetLatestPriceUseCase
import dev.marcocattaneo.gasprice.data.models.UIGasPrice
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLatestPriceUseCase: GetLatestPriceUseCase
): ViewModel() {

    private val mLastPriceLiveData = MutableLiveData<LiveDataResult<UIGasPrice>>()
    val lastPriceLiveData: LiveData<LiveDataResult<UIGasPrice>>
        get() = mLastPriceLiveData

    fun fetchLatestPrice() {
        viewModelScope.launch {
            mLastPriceLiveData.loading()
            try {
                mLastPriceLiveData.result(getLatestPriceUseCase.execute(null))
            } catch (e: Exception) {
                mLastPriceLiveData.error(e)
            }
        }
    }

}