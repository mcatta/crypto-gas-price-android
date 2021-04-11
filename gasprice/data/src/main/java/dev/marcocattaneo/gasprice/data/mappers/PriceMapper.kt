package dev.marcocattaneo.gasprice.data.mappers

import dev.marcocattaneo.gasprice.data.models.UIGasPrice
import dev.marcocattaneo.gasprice.domain.mapper.IMapper
import dev.marcocattaneo.gasprice.domain.models.GasPrice
import java.util.*
import javax.inject.Inject

class PriceMapper @Inject constructor() : IMapper<GasPrice, UIGasPrice> {

    override fun parseFromTo(from: GasPrice): UIGasPrice {
        return UIGasPrice(
            slow = UIGasPrice.Item(from.average.div(10), 5 * 60),
            fast = UIGasPrice.Item(from.fast.div(10), 2 * 60),
            fastest = UIGasPrice.Item(from.fastest.div(10), 30),
            lastUpdate = Date(from.createdAt.date.toEpochMilliseconds())
        )
    }

    override fun parseToFrom(to: UIGasPrice): GasPrice {
        throw IllegalStateException("Not implemented")
    }
}