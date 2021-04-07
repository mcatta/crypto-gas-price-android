package dev.marcocattaneo.gasprice.data.models

data class UIGasPrice(
    val fastest: Item,
    val fast: Item,
    val slow: Item
) {
    data class Item(
        val price: Int,
        val speed: Double
    )
}