package dev.marcocattaneo.gasprice.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class GasPrice(
    val rapid: Long,
    val fast: Long,
    val standard: Long,
    val slow: Long,
    val createdAt: FirestoreDate
)