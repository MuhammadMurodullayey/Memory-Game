package uz.gita.memorygame.ui.viewmodels.impls

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.memorygame.data.models.GameModel
import uz.gita.memorygame.data.models.LevelEnum
import uz.gita.memorygame.ui.viewmodels.GameViewModel
import uz.gita.memorygame.usecase.AllDataUseCase
import javax.inject.Inject

@HiltViewModel
class GameViewModelImpl @Inject constructor(
   private val useCase: AllDataUseCase
): ViewModel(), GameViewModel {
    private var isChecked = true
    private var isCheckedCount = 0

    override val getAllDataLiveData =  MutableLiveData<List<GameModel>>()
    override val popBackScreenLiveData= MutableLiveData<Unit>()
    override val closeClickImageLiveData = MutableLiveData<ImageView>()
    override val openClickImageLiveData = MutableLiveData<ImageView>()
    override val checkLiveData = MutableLiveData<Pair<GameModel,ImageView>>()
    override val shakeAnimateLiveData = MutableLiveData<ImageView>()
    override val timeLiveData = MutableLiveData<String>()
    lateinit var job : Job
    override val dialogLiveData = MutableLiveData<Pair<String, Int>>()

    override fun openDialog(text: String, color: Int) {
        dialogLiveData.value = Pair(text,color)
    }

    override fun shakeAnimate(view: ImageView) {
        shakeAnimateLiveData.value = view
    }

    override fun check(data: Pair<GameModel, ImageView>) {
     checkLiveData.value = data
    }

    override fun popBackScreen() {
        popBackScreenLiveData.value = Unit
    }

    override fun loadData(level: LevelEnum) {
        useCase.getAllDataByLevel(level).onEach {
            getAllDataLiveData.value = it
        }.launchIn(viewModelScope)
    }



    override fun closeClickImage(imageView: ImageView) {
         closeClickImageLiveData.value = imageView
    }
    override fun openClickImage(imageView: ImageView) {
        if (isChecked){
            isCheckedCount = 1 - isCheckedCount
            /*
            * a = 0
            * a = 1 - a = 1
            * a = 1 - a = 0
            * a = 1 - a = 1
            * a = 1 - a
            * a = 1 - a
            * a = 1 - a
            * a = 1 - a
            * a = 1 - a
            * a = 1 - a
            * a = 1 - a
            * a = 1 - a
            * a = 1 - a
            * */
            if (isCheckedCount == 0)
                isChecked = false
            openClickImageLiveData.value  = imageView
        }
    }


    override fun timeGetter(time: Int) {
        if(::job.isInitialized)
            job.cancel()
        var seconds = time * 60 - 1
        job = viewModelScope.launch(Dispatchers.IO) {
            while(seconds >= 0) {
                timeLiveData.postValue(timeFormatter(seconds))
                delay(1000)
                seconds--
            }
        }
    }

    override fun ok() {
        isChecked = true
    }

    private fun timeFormatter(seconds: Int) : String {
        val minute = seconds / 60
        val second = seconds % 60
        var answer = ""
        answer = "0$minute:" + (if(second < 10) "0$second" else "$second")
        return answer
    }
}