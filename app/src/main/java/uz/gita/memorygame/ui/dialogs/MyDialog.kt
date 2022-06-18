package uz.gita.memorygame.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.memorygame.R
import uz.gita.memorygame.databinding.ScreenDialogBinding

class MyDialog(val txt: String, val color: Int) : DialogFragment(R.layout.screen_dialog) {
    private val binding by viewBinding(ScreenDialogBinding::bind)
    private var onReloadClickListener : (() -> Unit)? = null
    private var onMenuClickListener : (() -> Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?. window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.reload1 .setOnClickListener {
            dismiss()
            onReloadClickListener?.invoke()
        }
        binding.menu1.setOnClickListener {
            dismiss()
            onMenuClickListener?.invoke()
        }
        binding.textDialog.text = txt
        binding.textDialog.setTextColor(color)
    }
    fun setonReloadClickListener(block : (()-> Unit)){
        onReloadClickListener = block
    }
    fun setonMenuClickListener(block : (()-> Unit)){
        onMenuClickListener = block
    }
}