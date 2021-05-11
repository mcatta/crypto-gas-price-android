package dev.marcocattaneo.push.data.sources

import dev.marcocattaneo.gasprice.common.repository.AuthenticationRepository
import dev.marcocattaneo.push.data.models.Alarm
import dev.marcocattaneo.push.data.repositories.AlarmsRepository
import dev.marcocattaneo.push.data.services.AlarmsService
import javax.inject.Inject

class AlarmsApi @Inject constructor(
    private val alarmsService: AlarmsService,
    private val authenticationRepository: AuthenticationRepository
) : AlarmsRepository {

    private suspend fun getAuthorizationToken() =
        "Bearer ${authenticationRepository.getAuthToken()}"

    override suspend fun getAll(): List<Alarm> = alarmsService.getAll(getAuthorizationToken())

    override suspend fun create(limit: Double): Alarm {
        return alarmsService.create(
            getAuthorizationToken(), mapOf(
                "limit" to limit
            )
        )
    }

    override suspend fun delete(id: String) = alarmsService.delete(getAuthorizationToken(), id)
}