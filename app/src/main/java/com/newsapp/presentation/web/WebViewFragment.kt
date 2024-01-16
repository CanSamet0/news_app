package com.newsapp.presentation.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.newsapp.data.model.Article
import com.newsapp.databinding.FragmentWebViewBinding


class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(layoutInflater)

        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webFragmentBackButton = binding.backArrowButtonWeb
        webFragmentBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val article: Article? = arguments?.getParcelable("article")
        println("article.url: ${article?.url}")

        article?.url?.let { url ->
            binding.webView.apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        }
    }
}