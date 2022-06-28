package com.professional_android.krishwordsassignment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.fragment.app.Fragment
import com.professional_android.krishwordsassignment.R
import com.professional_android.krishwordsassignment.databinding.MainFragmentBinding
import com.professional_android.krishwordsassignment.dialogs.ShowDialogAndOpenFragment

class MainFragment : Fragment() {

    lateinit var binding : MainFragmentBinding
    private val homeUrl = "https://krishworks.com/"
    private val aboutUsUrl = "https://krishworks.com/about/"
    private val updatesUrl = "https://krishworks.com/updates/"

    private lateinit var selectedButton : Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater,container,false)
        selectedButton = binding.btnHome

        initWebView()
        initListener()



        return binding.root
    }

    private fun initListener(){
        binding.btnHome.setOnClickListener{
            onButtonClick(it as Button,selectedButton,homeUrl)
            selectedButton = it
        }

        binding.btnAboutUs.setOnClickListener{
            onButtonClick(it as Button,selectedButton,aboutUsUrl)
            selectedButton = it
        }

        binding.btnUpdates.setOnClickListener{
            onButtonClick(it as Button,selectedButton,updatesUrl)
            selectedButton = it
        }

        binding.iconSetting.setOnClickListener {
            ShowDialogAndOpenFragment(requireContext()){
                parentFragmentManager.beginTransaction()
                    .add(R.id.mainContainer,SettingFragment())
                    .commit()
            }
        }
    }

    private fun initWebView(){
        binding.webViewMain.webViewClient = WebClient()
        binding.webViewMain.settings.javaScriptEnabled = true
        binding.webViewMain.loadUrl(homeUrl)

    }

    inner class WebClient : WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url!!)
            return false
        }
    }

    private fun onButtonClick(selectButton : Button,unselectButton : Button,url: String){
        binding.webViewMain.loadUrl(url)
        selectButton.setTextColor(resources.getColor(R.color.cBlue))
        selectButton.setBackgroundColor(resources.getColor(R.color.cWhite))

        unselectButton.setTextColor(resources.getColor(R.color.cWhite))
        unselectButton.setBackgroundColor(resources.getColor(R.color.cBlue))

    }


}