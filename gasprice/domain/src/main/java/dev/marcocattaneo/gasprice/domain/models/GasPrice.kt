package dev.marcocattaneo.gasprice.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class GasPrice(
    val fast: Int,
    val fastWait: Double,
    val fastest: Int,
    val fastestWait: Double,
    val safeLow: Int,
    val safeLowWait: Double,
    val average: Int,
    val avgWait: Double,
    val block_time: Double,
    val blockNum: Long,
    val speed: Double,
    val createdAt: FirestoreDate
)