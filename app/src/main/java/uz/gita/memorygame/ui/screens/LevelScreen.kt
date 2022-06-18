package uz.gita.memorygame.ui.screens

import android.annotation.SuppressLint
import android.database.Observable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.memorygame.R
import uz.gita.memorygame.data.models.LevelEnum
import uz.gita.memorygame.databinding.ScreenLevelBinding
import uz.gita.memorygame.ui.viewmodels.LevelViewModel
import uz.gita.memorygame.ui.viewmodels.impls.LevelViewModelIml

@AndroidEntryPoint
class LevelScreen : Fragment(R.layout.screen_level) {
    private val viewModel: LevelViewModel by viewModels<LevelViewModelIml>()
    private val binding by viewBinding(ScreenLevelBinding::bind)
    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.easy.setOnClickListener {
            viewModel.openNextScreen(LevelEnum.EASY)
        }
        binding.medium.setOnClickListener {
            viewModel.openNextScreen(LevelEnum.MEDIUM)
        }
        binding.hard.setOnClickListener {
            viewModel.openNextScreen(LevelEnum.HARD)
        }
        viewModel.openNextScreenLivedata.observe(this@LevelScreen,openNextScreenObserver)
    }
    private val openNextScreenObserver = Observer<LevelEnum>{
        findNavController().navigate(LevelScreenDirections.actionLevelScreenToGameScreen(it))
    }

}