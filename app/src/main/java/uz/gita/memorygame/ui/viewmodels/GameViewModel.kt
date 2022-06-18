package uz.gita.memorygame.ui.viewmodels

import android.widget.ImageView
import androidx.lifecycle.LiveData
import uz.gita.memorygame.data.models.GameModel
import uz.gita.memorygame.data.models.LevelEnum

interface GameViewModel {
  val getAllDataLiveData : LiveData<List<GameModel>>
  val popBackScreenLiveData : LiveData<Unit>
  val closeClickImageLiveData : LiveData<ImageView>
  val openClickImageLiveData : LiveData<ImageView>
  val checkLiveData : LiveData<Pair<GameModel,ImageView>>
  val shakeAnimateLiveData : LiveData<ImageView>
  val timeLiveData: LiveData<String>
  val dialogLiveData : LiveData< Pair<String,Int>>

  fun loadData(level: LevelEnum)
  fun check(data : Pair<GameModel,ImageView>)
  fun popBackScreen()
  fun shakeAnimate(view: ImageView)
  fun closeClickImage(imageView: ImageView)
  fun openClickImage(imageView: ImageView)
  fun openDialog(text : String,color : Int)

  fun timeGetter(time: Int)
  fun ok()
}