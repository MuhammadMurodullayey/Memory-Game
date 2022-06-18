package uz.gita.memorygame.ui.viewmodels.impls

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.memorygame.data.models.LevelEnum
import uz.gita.memorygame.ui.viewmodels.LevelViewModel
import javax.inject.Inject

@HiltViewModel
class LevelViewModelIml @Inject constructor(

): ViewModel(), LevelViewModel {
    override val openNextScreenLivedata = MutableLiveData<LevelEnum>()

    override fun openNextScreen(level: LevelEnum) {
        openNextScreenLivedata.value = level
    }

}
