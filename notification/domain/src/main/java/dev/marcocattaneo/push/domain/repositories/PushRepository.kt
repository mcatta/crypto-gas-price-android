package dev.marcocattaneo.push.domain.repositories

interface PushRepository {

    suspend fun registerDevice(pushToken: String)

}