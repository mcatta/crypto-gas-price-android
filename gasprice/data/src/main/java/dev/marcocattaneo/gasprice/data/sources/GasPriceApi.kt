package dev.marcocattaneo.gasprice.data.sources

import dev.marcocattaneo.gasprice.domain.models.GasPrice
import dev.marcocattaneo.gasprice.domain.repositories.GasPriceRepository

class GasPriceApi(

): GasPriceRepository {

    override suspend fun getGasPrice(): GasPrice {
        TODO("Not yet implemented")
    }

}