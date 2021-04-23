package dev.marcocattaneo.cryptogasprice.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import dev.marcocattaneo.cryptogasprice.R
import dev.marcocattaneo.cryptogasprice.main.MainActivity

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val splashViewModel by viewModels<SplashViewModel>()

    private val auth = FirebaseAuth.getInstance()

    private val observer = Observer<Unit?> {
        finish()
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel.registeringLiveData.observe(this, observer)

        auth.signInAnonymously().addOnCompleteListener(this@SplashActivity) { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                auth.getAccessToken(false).addOnCompleteListener { tokenTask ->
                    if (tokenTask.isSuccessful) {
                        splashViewModel.register(user?.uid ?: "", tokenTask.result?.token ?: "")
                    } else {
                        finish()
                    }
                }
            } else {
                finish()
            }
        }
    }

}