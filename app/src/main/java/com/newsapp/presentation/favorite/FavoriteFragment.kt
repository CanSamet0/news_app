package com.newsapp.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsapp.data.model.Article
import com.newsapp.databinding.FragmentFavoriteBinding
import com.newsapp.presentation.home.adapter.HomeRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getArticlesFromDb()
        initObservers()
    }

    private fun initObservers() {
        viewModel.favoriteArticlesLiveData.observe(viewLifecycleOwner){ articleList ->
            if (articleList.isEmpty()){
                setEmptyScreen()
            }else{
                setRecyclerView(articleList)
            }
        }
    }

    private fun setEmptyScreen() {
        binding.favoriteRv.visibility = View.GONE
        binding.emptyListText.visibility = View.VISIBLE
        binding.emptyScreenImageView.visibility = View.VISIBLE
    }

    private fun setRecyclerView(articleList: List<Article>) {

        val adapter = HomeRecyclerViewAdapter(
            list = articleList,
            onClickListener = { article ->
                getNewsDetailFragment(article)
            }
        )
        binding.favoriteRv.adapter = adapter
        binding.favoriteRv.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getNewsDetailFragment(article: Article) {
        FavoriteFragmentDirections.actionFavoriteFragmentToNewsDetailFragment(article).also {
            findNavController().navigate(it)
        }
    }


}