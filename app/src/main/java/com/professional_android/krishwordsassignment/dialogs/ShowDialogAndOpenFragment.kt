package com.professional_android.krishwordsassignment.dialogs

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.professional_android.krishwordsassignment.R
import com.professional_android.krishwordsassignment.databinding.KeybordDialogBinding
import com.professional_android.krishwordsassignment.databinding.PasscodeDialogBinding
import java.util.*

class ShowDialogAndOpenFragment(val context: Context, val openFragment : ()->Unit) {
    private var array : Array<EditText>
     var position = 0

    lateinit var passCodeDialog : Dialog
    lateinit var keybordDialog : BottomSheetDialog


    init{
        passCodeDialog = Dialog(context).apply {
            var binding = PasscodeDialogBinding.inflate(layoutInflater)
            setContentView(binding.root)

            array = arrayOf(binding.edt1,binding.edt2, binding.edt3, binding.edt4,binding.edt5,binding.edt6)

            binding.edt1.setOnClickListener(onPasscodeEditTextTab(binding.edt1))
            binding.edt2.setOnClickListener(onPasscodeEditTextTab(binding.edt1))
            binding.edt3.setOnClickListener(onPasscodeEditTextTab(binding.edt1))
            binding.edt4.setOnClickListener(onPasscodeEditTextTab(binding.edt1))
            binding.edt5.setOnClickListener(onPasscodeEditTextTab(binding.edt1))
            binding.edt6.setOnClickListener(onPasscodeEditTextTab(binding.edt1))

        }
        passCodeDialog.show()
    }

    inner class onPasscodeEditTextTab(val editText: EditText) : View.OnClickListener{
        override fun onClick(p0: View?) {
            showKeyboardDialog(context)
        }
    }

    fun showKeyboardDialog(context: Context){

        keybordDialog = BottomSheetDialog(context,R.style.keyBoardDialogTheme).apply {
            var binding = KeybordDialogBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.btn0.setOnClickListener(OnKeyBtnClick())
            binding.btn1.setOnClickListener(OnKeyBtnClick())
            binding.btn2.setOnClickListener(OnKeyBtnClick())
            binding.btn3.setOnClickListener(OnKeyBtnClick())
            binding.btn4.setOnClickListener(OnKeyBtnClick())
            binding.btn5.setOnClickListener(OnKeyBtnClick())
            binding.btn6.setOnClickListener(OnKeyBtnClick())
            binding.btn7.setOnClickListener(OnKeyBtnClick())
            binding.btn8.setOnClickListener(OnKeyBtnClick())
            binding.btn9.setOnClickListener(OnKeyBtnClick())
        }

        keybordDialog.show()

    }

    inner class OnKeyBtnClick : View.OnClickListener{
        override fun onClick(view: View?) {
            array[position++].setText((view as Button).text.toString())
            if (position < array.size) {
                array[position].requestFocus()
            }


            if (position == 6 && isAllEditTextFill()){
                position = 0
                val cal = Calendar.getInstance()
                val count = cal.get(Calendar.DAY_OF_MONTH) * (cal.get(Calendar.MONTH) + 1) * cal.get(Calendar.YEAR)

                val input = "${array[0].text.toString()}${array[1].text.toString()}${array[2].text.toString()}${array[3].text.toString()}${array[4].text.toString()}${array[5].text.toString()}".toInt()



                if (count == input){
                    keybordDialog.dismiss()
                    passCodeDialog.dismiss()
                    openFragment()
                }


            }
        }
    }

    private fun isAllEditTextFill() : Boolean{
        for (editText in array){
            if (editText.text.isEmpty()){
                return false
            }
        }
        return true
    }


}