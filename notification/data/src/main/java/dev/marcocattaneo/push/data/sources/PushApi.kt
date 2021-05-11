package dev.marcocattaneo.push.data.sources

import dev.marcocattaneo.gasprice.common.repository.AuthenticationRepository
import dev.marcocattaneo.push.data.repositories.PushRepository
import dev.marcocattaneo.push.data.services.PushService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PushApi @Inject constructor(
    private val pushService: PushService,
    private val authenticationRepository: AuthenticationRepository
) : PushRepository {

    private suspend fun getAuthorizationToken() =
        "Bearer ${authenticationRepository.getAuthToken()}"

    override suspend fun registerDevice(pushToken: String) = pushService.registerDeviceToken(
        getAuthorizationToken(), mapOf("token" to pushToken))

}