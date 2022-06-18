package uz.gita.memorygame.ui.viewmodels

import androidx.lifecycle.LiveData

interface SplashViewModel {
    val openNextScreenLiveData : LiveData<Unit>


    fun openNextScreen()
}