package dev.marcocattaneo.push.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlarmRequest(@SerialName("limit") val limit: Double)