package com.newsapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newsapp.data.model.Article
import com.newsapp.databinding.ItemHomeRvRowBinding
import com.newsapp.presentation.utils.getImageFromUrl

class HomeRecyclerViewAdapter(
    private val list: List<Article>,
    private val onClickListener: (Article) -> Unit
) : RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(private val binding: ItemHomeRvRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.newsTitle.text = article.title
            binding.newsDescription.text = article.description
            article.urlToImage?.let {
                binding.newsImage.getImageFromUrl(it)
            }
            binding.root.setOnClickListener {
                onClickListener(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeRvRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val article = list[position]
        holder.bind(article)
    }


}