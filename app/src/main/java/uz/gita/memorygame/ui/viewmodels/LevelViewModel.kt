package uz.gita.memorygame.ui.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.memorygame.data.models.LevelEnum

interface LevelViewModel {

    val openNextScreenLivedata : LiveData<LevelEnum>

    fun openNextScreen(level: LevelEnum)
}