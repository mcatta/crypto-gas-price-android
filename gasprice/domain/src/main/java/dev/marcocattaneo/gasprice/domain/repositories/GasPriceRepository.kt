package dev.marcocattaneo.gasprice.domain.repositories

import dev.marcocattaneo.gasprice.domain.models.GasPrice

interface GasPriceRepository {

    suspend fun getGasPrice(): GasPrice

    suspend fun getGasHistory(): List<GasPrice>

}