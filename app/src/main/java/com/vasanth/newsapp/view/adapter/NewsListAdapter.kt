package com.vasanth.newsapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vasanth.newsapp.R
import com.vasanth.newsapp.view.listener.NewsArticleListener
import com.vasanth.presentation.model.NewsArticleUIModel
import javax.inject.Inject


class NewsListAdapter @Inject constructor() :
    RecyclerView.Adapter<NewsListAdapter.NewsItemViewHolder>() {

    // Properties.
    var newsArticles: List<NewsArticleUIModel> = arrayListOf()
    var newsArticleListener: NewsArticleListener? = null

    // View Holder.
    inner class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivImage: ImageView
        val tvTitle: TextView
        val tvAdditionalInfo: TextView

        init {
            ivImage = itemView.findViewById(R.id.image)
            tvTitle = itemView.findViewById(R.id.title)
            tvAdditionalInfo = itemView.findViewById(R.id.additional_info)
        }

    }

    // RecyclerView.Adapter Methods.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val newsArticle = newsArticles.get(position)

        val additionalInfo = "${newsArticle.source} - ${newsArticle.publishedAt}"

        Glide.with(holder.ivImage.context)
            .load(newsArticle.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.ic_default_thumbnail)
            .into(holder.ivImage)

        holder.tvTitle.text = newsArticle.title
        holder.tvAdditionalInfo.text = additionalInfo

        holder.itemView.setOnClickListener {
            newsArticleListener?.onNewsArticleClicked(newsArticle)
        }
    }

    override fun getItemCount(): Int {
        return newsArticles.size
    }
}