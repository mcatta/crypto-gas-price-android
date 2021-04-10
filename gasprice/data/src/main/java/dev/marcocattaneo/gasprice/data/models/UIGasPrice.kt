package dev.marcocattaneo.gasprice.data.models

import java.util.*

data class UIGasPrice(
    val fastest: Item,
    val fast: Item,
    val slow: Item,
    val lastUpdate: Date
) {
    data class Item(
        val price: Int,
        val speedSeconds: Int
    )
}