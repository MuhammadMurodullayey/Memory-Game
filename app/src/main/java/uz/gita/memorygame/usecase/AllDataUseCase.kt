package uz.gita.memorygame.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.memorygame.data.models.GameModel
import uz.gita.memorygame.data.models.LevelEnum

interface AllDataUseCase {
    fun getAllDataByLevel(level : LevelEnum) : Flow<List<GameModel>>
}