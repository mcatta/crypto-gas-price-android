package dev.marcocattaneo.gasprice.domain.models

data class GasPrice(
    val fast: Number,
    val fastest: Number,
    val safeLow: Number,
    val average: Number,
    val block_time: Number,
    val blockNum: Long,
    val speed: Number,
    val safeLowWait: Number,
    val avgWait: Number,
    val fastestWait: Number
)