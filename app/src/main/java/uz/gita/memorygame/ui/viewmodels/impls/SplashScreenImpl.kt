package uz.gita.memorygame.ui.viewmodels.impls

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.memorygame.ui.viewmodels.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenImpl @Inject constructor(): ViewModel(), SplashViewModel {
    override val openNextScreenLiveData =  MutableLiveData<Unit>()

    override fun openNextScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            openNextScreenLiveData.postValue(Unit)
        }
    }
}