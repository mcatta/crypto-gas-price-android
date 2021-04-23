package dev.marcocattaneo.gasprice.data.mappers

import dev.marcocattaneo.gasprice.common.mapper.IMapper
import dev.marcocattaneo.gasprice.data.models.UIGasPrice
import dev.marcocattaneo.gasprice.domain.models.GasPrice
import java.util.*
import javax.inject.Inject

class PriceMapper @Inject constructor() : IMapper<GasPrice, UIGasPrice> {

    override fun parseFromTo(from: GasPrice): UIGasPrice {
        return UIGasPrice(
            slow = UIGasPrice.Item(from.slow.div(1000000000).toInt(), 5 * 60),
            fast = UIGasPrice.Item(from.fast.div(1000000000).toInt(), 2 * 60),
            fastest = UIGasPrice.Item(from.rapid.div(1000000000).toInt(), 30),
            lastUpdate = Date(from.createdAt.date.toEpochMilliseconds())
        )
    }

    override fun parseToFrom(to: UIGasPrice): GasPrice {
        throw IllegalStateException("Not implemented")
    }
}