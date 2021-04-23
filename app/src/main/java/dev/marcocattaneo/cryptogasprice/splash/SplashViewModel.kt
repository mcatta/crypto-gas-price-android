package dev.marcocattaneo.cryptogasprice.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.marcocattaneo.gasprice.common.repository.AuthenticationRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val mRegisteringLiveData = MutableLiveData<Unit?>()
    val registeringLiveData: LiveData<Unit?>
        get() = mRegisteringLiveData

    fun register(userId: String, token: String) {
        viewModelScope.launch {
            authenticationRepository.writeAuth(userId, token)

            mRegisteringLiveData.value = null
        }
    }

}