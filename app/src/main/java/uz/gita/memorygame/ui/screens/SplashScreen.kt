package uz.gita.memorygame.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.memorygame.R
import uz.gita.memorygame.ui.viewmodels.SplashViewModel
import uz.gita.memorygame.ui.viewmodels.impls.SplashScreenImpl

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel : SplashViewModel by viewModels<SplashScreenImpl> ()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openNextScreen()
        viewModel.openNextScreenLiveData.observe(viewLifecycleOwner,openNextScreenObserver)
    }
    private val openNextScreenObserver = Observer<Unit>{
        findNavController().navigate(R.id.action_splashScreen_to_levelScreen)
    }
}