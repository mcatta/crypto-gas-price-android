package dev.marcocattaneo.cryptogasprice

import androidx.lifecycle.MutableLiveData
import dev.marcocattaneo.cryptogasprice.utils.LiveDataResult


fun <T> MutableLiveData<LiveDataResult<T>>.loading(loading: Boolean = true) {
    this.value = LiveDataResult.Loading(loading)
}

fun <T> MutableLiveData<LiveDataResult<T>>.result(result: T) {
    this.value = LiveDataResult.Success(result)
}

fun <T> MutableLiveData<LiveDataResult<T>>.error(err: Exception) {
    this.value = LiveDataResult.Error(err)
}