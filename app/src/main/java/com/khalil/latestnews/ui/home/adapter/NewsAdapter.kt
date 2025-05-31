package com.khalil.latestnews.ui.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khalil.latestnews.R
import com.khalil.latestnews.databinding.ItemNewsBinding
import com.khalil.latestnews.doman.model.Article


class NewsAdapter(
    private val onFavoriteClick: (Article) -> Unit,
    private val onNewsClick: (Article) -> Unit
) : ListAdapter<Article, NewsAdapter.NewsHolder>(NewsDiffCallback()) {

    inner class NewsHolder(
        val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            with(binding) {
                textTitle.text = article.title
                textDate.text = article.publishedAt
                textDesc.text = article.description
                iconHeart.setImageResource(
                    if (article.isFavorite) R.drawable.ic_heart_red
                    else R.drawable.ic_favorite
                )
                Glide.with(itemView.context)
                    .load(article.urlToImage)
                    .into(imageNews)

                iconHeart.setOnClickListener {
                    onFavoriteClick(article)
                }
                root.setOnClickListener {
                    onNewsClick(article)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }
}



class NewsDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean = oldItem.publishedAt == newItem.publishedAt
    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean = oldItem == newItem
}