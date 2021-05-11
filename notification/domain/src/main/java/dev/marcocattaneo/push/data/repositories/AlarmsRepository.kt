package dev.marcocattaneo.push.data.repositories

import dev.marcocattaneo.push.data.models.Alarm

interface AlarmsRepository {

    suspend fun getAll(): List<Alarm>

    suspend fun create(limit: Double): Alarm

    suspend fun delete(id: String)

}