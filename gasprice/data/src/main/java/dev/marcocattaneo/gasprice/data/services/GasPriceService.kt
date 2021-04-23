package dev.marcocattaneo.gasprice.data.services

import dev.marcocattaneo.gasprice.common.Constants
import dev.marcocattaneo.gasprice.domain.models.GasPrice
import retrofit2.http.GET
import retrofit2.http.Header

interface GasPriceService {

    @GET("histories")
    suspend fun getHistories(@Header(Constants.HTTP_HEADER_AUTHORIZATION) bearer: String): List<GasPrice>

    @GET("histories/latest")
    suspend fun getLatest(@Header(Constants.HTTP_HEADER_AUTHORIZATION) bearer: String): GasPrice

}