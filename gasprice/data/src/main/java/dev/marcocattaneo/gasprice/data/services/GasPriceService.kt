package dev.marcocattaneo.gasprice.data.services

import dev.marcocattaneo.gasprice.domain.models.GasPrice
import retrofit2.http.GET

interface GasPriceService {

    @GET("histories")
    suspend fun getHistories(): List<GasPrice>

    @GET("histories/latest")
    suspend fun getLatest(): GasPrice

}