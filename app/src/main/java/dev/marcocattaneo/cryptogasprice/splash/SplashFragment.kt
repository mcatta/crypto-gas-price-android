package dev.marcocattaneo.cryptogasprice.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import dev.marcocattaneo.cryptogasprice.R
import dev.marcocattaneo.cryptogasprice.MainActivity

@AndroidEntryPoint
@ExperimentalMaterialApi
class SplashFragment : Fragment() {

    private val splashViewModel by activityViewModels<SplashViewModel>()
    private val auth = FirebaseAuth.getInstance()
    private val observer = Observer<Unit?> {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, MainActivity.MainFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        splashViewModel.registeringLiveData.observe(viewLifecycleOwner, observer)

        auth.signInAnonymously().addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                auth.getAccessToken(false).addOnCompleteListener { tokenTask ->
                    if (tokenTask.isSuccessful) {
                        splashViewModel.register(user?.uid ?: "", tokenTask.result?.token ?: "")
                    } else {
                        activity?.finish()
                    }
                }
            } else {
                activity?.finish()
            }
        }
    }

}