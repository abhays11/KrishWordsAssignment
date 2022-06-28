package com.professional_android.krishwordsassignment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.professional_android.krishwordsassignment.R
import com.professional_android.krishwordsassignment.databinding.PasscodeDialogBinding
import com.professional_android.krishwordsassignment.databinding.SettingFragmentBinding

class SettingFragment : Fragment() {
    lateinit var binding: SettingFragmentBinding
    val galleryUrl = "https://krishworks.com/gallery/"
    val contactUsUrl = "https://krishworks.com/contact/"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingFragmentBinding.inflate(inflater,container,false)
        initWebView()
        initListener()

        binding.root.setOnClickListener {  }
        return binding.root
    }

    private fun initListener(){
        binding.txtClose.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .remove(this)
                .commit()
        }

        binding.txtGallery.setOnClickListener {
            binding.webViewMain.loadUrl(galleryUrl)
            binding.layoutGallery.setBackgroundColor(resources.getColor(R.color.cGrey))
            binding.txtGallery.setTextColor(resources.getColor(R.color.cYellow))


            binding.layoutContactUs.setBackgroundColor(resources.getColor(R.color.cWhite))
            binding.txtContactUs.setTextColor(resources.getColor(R.color.cGrey))
        }

        binding.txtContactUs.setOnClickListener {
            binding.webViewMain.loadUrl(contactUsUrl)
            binding.layoutContactUs.setBackgroundColor(resources.getColor(R.color.cGrey))
            binding.txtContactUs.setTextColor(resources.getColor(R.color.cYellow))


            binding.layoutGallery.setBackgroundColor(resources.getColor(R.color.cWhite))
            binding.txtGallery.setTextColor(resources.getColor(R.color.cGrey))
        }
    }

    private fun initWebView(){
        binding.webViewMain.webViewClient = WebClient()
        binding.webViewMain.settings.javaScriptEnabled = true
        binding.webViewMain.loadUrl(galleryUrl)
    }

    inner class WebClient : WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url!!)
            return false
        }
    }
}