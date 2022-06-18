package uz.gita.memorygame.ui.screens

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.memorygame.R
import uz.gita.memorygame.data.models.GameModel
import uz.gita.memorygame.data.models.LevelEnum
import uz.gita.memorygame.databinding.ScreenGameBinding
import uz.gita.memorygame.ui.dialogs.MyDialog
import uz.gita.memorygame.ui.viewmodels.GameViewModel
import uz.gita.memorygame.ui.viewmodels.impls.GameViewModelImpl

@AndroidEntryPoint
class GameScreen : Fragment(R.layout.screen_game) {
    private val viewModel: GameViewModel by viewModels<GameViewModelImpl>()
    private val binding by viewBinding(ScreenGameBinding::bind)
    private val args: GameScreenArgs by navArgs()
    private var level = LevelEnum.EASY
    private var allData = ArrayList<GameModel>()

    private var count = 0
    private var id1 = 0
    private var id2 = 0
    private var steps = 0
    private var correctAnswerCount = 0
    private var correctAnswerByLevel = 0

    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private var _height = 0
    private var _width = 0
    private val views = ArrayList<ImageView>()
    private var started = false
     private var count2 = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        args.arg.apply {
            level = this
            when (level) {
                LevelEnum.EASY -> binding.time.text = "02:00"
                LevelEnum.MEDIUM -> binding.time.text = "04:00"
                LevelEnum.HARD -> binding.time.text = "06:00"
            }
            correctAnswerByLevel = x * y /2
        }
        binding.main.post {
            _height = (binding.main.height / (level.y + 2))
            _width = (binding.main.width / (level.x + 1))
            binding.container.layoutParams.apply {
                height = _height * level.y
                width = _width * level.x
            }
            load()
            viewModel.loadData(level)
        }

        viewModel.getAllDataLiveData.observe(viewLifecycleOwner, getAllDataObserver)
        viewModel.popBackScreenLiveData.observe(viewLifecycleOwner, popBackScreenObserver)
        viewModel.openClickImageLiveData.observe(viewLifecycleOwner, openClickImageObserver)
        viewModel.closeClickImageLiveData.observe(viewLifecycleOwner, closeClickImageObserver)
        viewModel.checkLiveData.observe(viewLifecycleOwner, checkObserver)
        viewModel.shakeAnimateLiveData.observe(viewLifecycleOwner, shakeAnimateObserver)
        viewModel.timeLiveData.observe(viewLifecycleOwner, timeObserver)
        viewModel.dialogLiveData.observe(viewLifecycleOwner,dialogObserver)

        binding.menu.setOnClickListener {
            viewModel.popBackScreen()
        }
        binding.reload.setOnClickListener {
            viewModel.loadData(level)
            for (i in views.indices) {
                viewModel.closeClickImage(views[i])
            }
            steps = 0
            binding.triesText.text = steps.toString()
            started = false
            viewModel.timeGetter(0)
            when (level) {
                LevelEnum.EASY -> binding.time.text = "02:00"
                LevelEnum.MEDIUM -> binding.time.text = "04:00"
                LevelEnum.HARD -> binding.time.text = "06:00"
            }
        }
    }
    private val dialogObserver = Observer<Pair<String,Int>>{
        val dialog = MyDialog(it.first, it.second)
        dialog.isCancelable = false
        dialog.setonReloadClickListener {
            viewModel.loadData(level)
            for (i in views.indices) {
                viewModel.closeClickImage(views[i])
            }
            steps = 0
            binding.triesText.text = steps.toString()
            started = false
            viewModel.timeGetter(0)
            when (level) {
                LevelEnum.EASY -> binding.time.text = "02:00"
                LevelEnum.MEDIUM -> binding.time.text = "04:00"
                LevelEnum.HARD -> binding.time.text = "06:00"
            }
        }
        dialog.setonMenuClickListener {
            viewModel.popBackScreen()
        }
        dialog.show(childFragmentManager,"dialog")
    }

    private val timeObserver = Observer<String> {
        binding.time.text = it
        if(it.equals("00:00")){
            viewModel.openDialog("Game Over", Color.RED)
        }
    }

    private val shakeAnimateObserver = Observer<ImageView> {
        it.apply {
            isEnabled = false
            animate().setDuration(50).translationXBy(-20f).withEndAction {
                animate().setDuration(50).translationXBy(40f).withEndAction {
                    animate().setDuration(50).translationXBy(-20f).withEndAction {
                        isEnabled = true
                    }
                }
            }.start()
        }
    }
    private val checkObserver = Observer<Pair<GameModel, ImageView>> {
        when (count) {
            0 -> {
                id1 = it.first.id
                imageView1 = it.second
                count++
            }
            1 -> {
                id2 = it.first.id
                imageView2 = it.second

                if (id1 != id2) {
                    viewModel.closeClickImage(imageView1)
                    viewModel.closeClickImage(imageView2)


                }else{
                    viewModel.ok()
                    correctAnswerCount++
                    if(correctAnswerCount == correctAnswerByLevel){
                        viewModel.openDialog("You Win", Color.BLUE)
                    }
                }
//                else {
//                    imageView1.animate().setDuration(200).alpha(0f).withEndAction {
//                        imageView1.visibility = View.INVISIBLE
//                    }.start()
//                    imageView2.animate().setDuration(200).alpha(0f).withEndAction {
//                        imageView2.visibility = View.INVISIBLE
//                    }.start()
//                }
                id1 = 0
                id2 = 0
                count = 0
                steps++
                binding.triesText.text = steps.toString()
            }
        }

    }
    private val openClickImageObserver = Observer<ImageView> { imageView ->

        var model = imageView.tag as GameModel
        imageView.animate().setDuration(200).rotationY(89f).withEndAction {
            Glide.with(binding.root)
                .load(model.image)
                .into(imageView)
            imageView.animate().setDuration(200).rotationY(180f).setInterpolator(DecelerateInterpolator()).withEndAction {
                viewModel.check(Pair(model, imageView))

            }
        }.start()
    }
    private val closeClickImageObserver = Observer<ImageView> { imageView ->
        imageView.animate().setDuration(200).rotationY(91f).withEndAction {
            Glide.with(binding.root)
                .load(R.drawable.image_back)
                .into(imageView)
            imageView.animate().setDuration(200).rotationY(0f).setInterpolator(DecelerateInterpolator()).withEndAction {
             viewModel.ok()
            }
        }.start()
    }
    private val popBackScreenObserver = Observer<Unit> {
        findNavController().popBackStack()
    }
    private val getAllDataObserver = Observer<List<GameModel>> { list ->
        allData = list as ArrayList<GameModel>
        for (i in list.indices) {

            views[i].tag = list[i]
            views[i].setOnClickListener {
                if (!started) {
                    when (level) {
                        LevelEnum.EASY -> viewModel.timeGetter(2)
                        LevelEnum.MEDIUM -> viewModel.timeGetter(4)
                        LevelEnum.HARD -> viewModel.timeGetter(6)
                    }
                    started = true
                }
                if (views[i].rotationY == 0f) {
                    viewModel.openClickImage(it as ImageView)
                } else {
                    viewModel.shakeAnimate(it as ImageView)
                }
            }
        }
    }


    private fun load() {
        for (i in 0 until level.x) {
            for (j in 0 until level.y) {
                val image = ImageView(requireContext())
                binding.container.addView(image)
                val lp = image.layoutParams as RelativeLayout.LayoutParams
                lp.apply {
                    height = _height
                    width = _width
                }
                image.x = i * _width * 1f
                image.y = j * _height * 1f
                image.scaleType = ImageView.ScaleType.FIT_XY
                lp.setMargins(4, 4, 4, 4)
                image.setImageResource(R.drawable.image_back)
                image.layoutParams = lp
                views.add(image)
            }
        }

    }

}