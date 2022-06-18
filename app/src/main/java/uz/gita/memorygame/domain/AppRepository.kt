package uz.gita.memorygame.domain

import uz.gita.memorygame.data.models.GameModel
import uz.gita.memorygame.data.models.LevelEnum

interface AppRepository {
    fun getAllData(level  :LevelEnum) : List<GameModel>
}