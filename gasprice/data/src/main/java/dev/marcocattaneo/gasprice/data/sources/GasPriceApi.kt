package dev.marcocattaneo.gasprice.data.sources

import dev.marcocattaneo.gasprice.common.repository.AuthenticationRepository
import dev.marcocattaneo.gasprice.data.services.GasPriceService
import dev.marcocattaneo.gasprice.domain.models.GasPrice
import dev.marcocattaneo.gasprice.domain.repositories.GasPriceRepository

class GasPriceApi constructor(
    private val gasPriceService: GasPriceService,
    private val authenticationRepository: AuthenticationRepository
) : GasPriceRepository {

    private suspend fun getAuthorizationToken() = "Bearer ${authenticationRepository.getAuthToken()}"

    override suspend fun getGasPrice(): GasPrice =
        gasPriceService.getLatest(getAuthorizationToken())

    override suspend fun getGasHistory() =
        gasPriceService.getHistories(getAuthorizationToken())

}