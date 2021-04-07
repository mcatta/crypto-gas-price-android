package dev.marcocattaneo.cryptogasprice.utils

sealed class LiveDataResult<T> {

    data class Success<T>(val data: T): LiveDataResult<T>()

    data class Loading<T>(val loading: Boolean = true): LiveDataResult<T>()

    data class Error<T>(val error: Exception): LiveDataResult<T>()

}