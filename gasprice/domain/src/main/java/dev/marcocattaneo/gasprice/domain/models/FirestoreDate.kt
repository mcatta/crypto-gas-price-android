package dev.marcocattaneo.gasprice.domain.models

import kotlinx.datetime.Instant
import kotlinx.datetime.Instant.Companion.fromEpochSeconds
import kotlinx.serialization.Serializable

@Serializable
data class FirestoreDate(
    val _seconds: Long,
    val _nanoseconds: Long
) {
    val date: Instant
        get() = fromEpochSeconds(_seconds)
}