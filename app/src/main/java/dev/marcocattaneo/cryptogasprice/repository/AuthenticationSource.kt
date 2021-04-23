package dev.marcocattaneo.cryptogasprice.repository

import android.content.SharedPreferences
import dev.marcocattaneo.gasprice.common.repository.AuthenticationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AuthenticationRepository {

    companion object {
        const val PREF_USER_ID = "dev.marcocattaneo.auth.data.USER_ID"
        const val PREF_USER_TOKEN = "dev.marcocattaneo.auth.data.USER_TOKEN"
    }

    override suspend fun writeAuth(userID: String, authToken: String) {
        sharedPreferences.edit()
            .putString(PREF_USER_ID, userID)
            .putString(PREF_USER_TOKEN, authToken)
            .apply()
    }

    override suspend fun getUserId(): String = sharedPreferences.getString(PREF_USER_ID, "") ?: ""

    override suspend fun getAuthToken(): String =
        sharedPreferences.getString(PREF_USER_TOKEN, "") ?: ""

}