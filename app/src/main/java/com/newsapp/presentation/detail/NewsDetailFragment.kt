package com.newsapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.newsapp.R
import com.newsapp.data.model.Article
import com.newsapp.databinding.FragmentNewsDetailBinding
import com.newsapp.presentation.utils.getImageFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {

    private lateinit var binding: FragmentNewsDetailBinding
    private val viewModel: NewsDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setContext(requireContext())
        val article: Article? = arguments?.getParcelable("article")
        article?.let {
            val newsTitle = binding.newsHeadline
            val newsDetail = binding.newsDetailText
            val newsAuthor = binding.authorNameText
            val newsDate = binding.dateText
            val newsImage = binding.newsImage

            newsTitle.text = article.title
            newsDetail.text = article.description
            newsAuthor.text = article.author
            newsDate.text = article.publishedAt
            newsImage.getImageFromUrl(article.urlToImage.toString())

            val favoriteButton = binding.favoriteButton

            article.url?.let {
                viewModel.checkBookmarked(article.url)
            }

            favoriteButton.setOnClickListener {
                viewModel.setBookmarked(article)
            }

            val newsSourceButton = binding.newsSourceButton
            newsSourceButton.setOnClickListener {_ ->
                NewsDetailFragmentDirections.actionNewsDetailFragmentToWebViewFragment(it).also {
                    findNavController().navigate(it)
                }
            }

            initObservers()

        }
        val backButton = binding.backArrowButton
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val goToFavButton = binding.uploadButton
        goToFavButton.setOnClickListener {
            NewsDetailFragmentDirections.actionNewsDetailFragmentToFavoriteFragment().also {
                findNavController().navigate(it)
            }
        }





    }

    private fun initObservers() {
        viewModel.isBookmarkedLiveData.observe(viewLifecycleOwner){ value ->
            if (value){
                binding.favoriteButton.setImageResource(R.drawable.favorite_second)
            }else{
                binding.favoriteButton.setImageResource(R.drawable.favorite_icon)
            }
        }
    }


}