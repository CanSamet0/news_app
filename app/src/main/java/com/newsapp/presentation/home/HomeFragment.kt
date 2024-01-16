package com.newsapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsapp.R
import com.newsapp.data.Resource
import com.newsapp.data.model.Article
import com.newsapp.data.model.NewsRootResponse
import com.newsapp.databinding.FragmentHomeBinding
import com.newsapp.presentation.home.adapter.HomeRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getSearchedNews(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        viewModel.getTopHeadlines()
        observeLivedata()

    }

    private fun observeLivedata() {
        viewModel.newsLiveData.observe(viewLifecycleOwner) { resource: Resource<NewsRootResponse> ->
            when (resource) {
                is Resource.Loading -> {
                    binding.homeProgressBar.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                }

                is Resource.Success -> {
                    binding.homeProgressBar.visibility = View.GONE
                    binding.tvError.visibility = View.GONE
                    val newsResponse: NewsRootResponse? = resource.data
                    newsResponse?.let { newsRootResponse ->
                        val list: List<Article> = newsRootResponse.articles
                        setRecyclerView(list)
                    }
                }

                is Resource.Error -> {
                    binding.homeProgressBar.visibility = View.GONE
                    binding.tvError.visibility = View.VISIBLE
                }
            }
        }

        viewModel.searchQueryLiveData.observe(viewLifecycleOwner) { query ->
            if (query.isNotBlank()) {
                viewModel.getSearchedNews(query)
            }
        }
    }

    private fun setRecyclerView(list: List<Article>) {
        val adapter = HomeRecyclerViewAdapter(
            list = list,
            onClickListener = { article ->
                getNewsDetailFragment(article)
            }
        )
        binding.rvHomeNews.adapter = adapter
        binding.rvHomeNews.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getNewsDetailFragment(article: Article) {

        HomeFragmentDirections.actionHomeFragmentToNewsDetailFragment(article).also {
            findNavController().navigate(it)
        }
    }

}