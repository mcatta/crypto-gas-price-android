package dev.marcocattaneo.push.data.repositories

interface PushRepository {

    suspend fun registerDevice(pushToken: String)

}