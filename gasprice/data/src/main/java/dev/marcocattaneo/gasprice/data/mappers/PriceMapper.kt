package dev.marcocattaneo.gasprice.data.mappers

import dev.marcocattaneo.gasprice.data.models.UIGasPrice
import dev.marcocattaneo.gasprice.domain.mapper.IMapper
import dev.marcocattaneo.gasprice.domain.models.GasPrice
import javax.inject.Inject

class PriceMapper @Inject constructor() : IMapper<GasPrice, UIGasPrice> {

    override fun parseFromTo(from: GasPrice): UIGasPrice {
        return UIGasPrice(
            slow = UIGasPrice.Item(from.safeLow.div(10), from.safeLowWait),
            fast = UIGasPrice.Item(from.fast.div(10), from.fastWait),
            fastest = UIGasPrice.Item(from.fastest.div(10), from.fastestWait)
        )
    }

    override fun parseToFrom(to: UIGasPrice): GasPrice {
        throw IllegalStateException("Not implemented")
    }
}