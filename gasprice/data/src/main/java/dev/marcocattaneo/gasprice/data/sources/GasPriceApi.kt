package dev.marcocattaneo.gasprice.data.sources

import dev.marcocattaneo.gasprice.data.services.GasPriceService
import dev.marcocattaneo.gasprice.domain.models.GasPrice
import dev.marcocattaneo.gasprice.domain.repositories.GasPriceRepository

class GasPriceApi constructor(
    private val gasPriceService: GasPriceService
) : GasPriceRepository {

    override suspend fun getGasPrice(): GasPrice = gasPriceService.getLatest()

    override suspend fun getGasHistory() = gasPriceService.getHistories()

}