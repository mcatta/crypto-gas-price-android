package dev.marcocattaneo.gasprice.data.interactors

import dev.marcocattaneo.gasprice.common.interactor.AbsUseCase
import dev.marcocattaneo.gasprice.data.di.GasApiScope
import dev.marcocattaneo.gasprice.data.mappers.PriceMapper
import dev.marcocattaneo.gasprice.data.models.UIGasPrice
import dev.marcocattaneo.gasprice.domain.repositories.GasPriceRepository
import javax.inject.Inject

class GetLatestPriceUseCase @Inject constructor(
    @GasApiScope private val apiRepository: GasPriceRepository,
    private val mapper: PriceMapper
) : AbsUseCase<Nothing?, UIGasPrice>() {

    override suspend fun execute(input: Nothing?): UIGasPrice {
        return apiRepository.getGasPrice().let {
            mapper.parseFromTo(it)
        }
    }

}