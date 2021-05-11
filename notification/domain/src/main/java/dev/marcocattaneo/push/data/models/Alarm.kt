package dev.marcocattaneo.push.data.models

import dev.marcocattaneo.gasprice.common.models.FirestoreDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Alarm(
    @SerialName("id")
    val id: String? = null,

    @SerialName("user_id")
    val userId: String,

    @SerialName("limit")
    val limit: Double,

    @SerialName("enabled")
    val enabled: Boolean,

    @SerialName("createdAt")
    val createdAt: FirestoreDate,

    @SerialName("updatedAt")
    val updatedAt: FirestoreDate
)