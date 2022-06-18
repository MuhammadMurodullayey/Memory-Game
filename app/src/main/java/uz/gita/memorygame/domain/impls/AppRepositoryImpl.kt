package uz.gita.memorygame.domain.impls

import uz.gita.memorygame.R
import uz.gita.memorygame.data.models.GameModel
import uz.gita.memorygame.data.models.LevelEnum
import uz.gita.memorygame.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {
    private val list = ArrayList<GameModel>()

    init {
        load()
    }
    private fun load(){
        list.add(GameModel(1, R.drawable.image_1))
        list.add(GameModel(2, R.drawable.image_2))
        list.add(GameModel(3, R.drawable.image_3))
        list.add(GameModel(4, R.drawable.image_4))
        list.add(GameModel(5, R.drawable.image_5))
        list.add(GameModel(6, R.drawable.image_6))
        list.add(GameModel(7, R.drawable.image_7))
        list.add(GameModel(8, R.drawable.image_8))
        list.add(GameModel(9, R.drawable.image_9))
        list.add(GameModel(10, R.drawable.image_10))
        list.add(GameModel(11, R.drawable.image_11))
        list.add(GameModel(12, R.drawable.image_12))
        list.add(GameModel(13, R.drawable.image_13))
        list.add(GameModel(14, R.drawable.image_14))
        list.add(GameModel(15, R.drawable.image_15))
        list.add(GameModel(16, R.drawable.image_16))
        list.add(GameModel(17, R.drawable.image_17))
        list.add(GameModel(18, R.drawable.image_18))
        list.add(GameModel(19, R.drawable.image_19))
        list.add(GameModel(20, R.drawable.image_20))
        list.add(GameModel(21, R.drawable.image_21))
        list.add(GameModel(22, R.drawable.image_22))
        list.add(GameModel(23, R.drawable.image_23))
        list.add(GameModel(24, R.drawable.image_24))
        list.add(GameModel(25, R.drawable.image_25))
        list.add(GameModel(26, R.drawable.image_26))
        list.add(GameModel(28, R.drawable.image_28))
        list.add(GameModel(29, R.drawable.image_29))
        list.add(GameModel(30, R.drawable.image_30))
        list.add(GameModel(31, R.drawable.image_31))
        list.add(GameModel(32, R.drawable.image_32))
        list.add(GameModel(33, R.drawable.image_33))
        list.add(GameModel(34, R.drawable.image_34))
        list.add(GameModel(35, R.drawable.image_35))
        list.add(GameModel(36, R.drawable.image_36))
        list.add(GameModel(37, R.drawable.image_37))
        list.add(GameModel(38, R.drawable.image_38))
        list.add(GameModel(39, R.drawable.image_39))
        list.add(GameModel(40, R.drawable.image_40))

    }
    override fun getAllData(level: LevelEnum): List<GameModel> {
        val count = level.x * level.y
        list.shuffle()
        return list.subList(0,count/2)
    }
}