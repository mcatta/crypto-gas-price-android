package dev.marcocattaneo.gasprice.common.repository

interface AuthenticationRepository {

    suspend fun writeAuth(userID: String, authToken: String)

    suspend fun getUserId(): String

    suspend fun getAuthToken(): String

}