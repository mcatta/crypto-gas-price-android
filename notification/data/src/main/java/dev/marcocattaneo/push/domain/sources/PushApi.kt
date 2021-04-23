package dev.marcocattaneo.push.domain.sources

import dev.marcocattaneo.gasprice.common.repository.AuthenticationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PushApi @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {

}