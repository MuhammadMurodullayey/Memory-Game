package uz.gita.memorygame.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import uz.gita.memorygame.data.models.GameModel
import uz.gita.memorygame.data.models.LevelEnum
import uz.gita.memorygame.domain.AppRepository
import uz.gita.memorygame.usecase.AllDataUseCase
import javax.inject.Inject

class AllDataUseCaseImpl @Inject constructor(
   private val repository: AppRepository
): AllDataUseCase {
    override fun getAllDataByLevel(level: LevelEnum): Flow<List<GameModel>>  = flow {
        val list = ArrayList<GameModel>()
        val  v = repository.getAllData(level)
        list.addAll(v)
        list.addAll(v)
        list.shuffle()
         emit(list)
    }.flowOn(Dispatchers.IO)

}