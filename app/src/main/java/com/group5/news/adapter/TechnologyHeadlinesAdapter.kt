package com.group5.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.group5.news.data.Article
import com.group5.news.databinding.RvTechnologyHeadlinesBinding

class TechnologyHeadlinesAdapter : RecyclerView.Adapter<TechnologyHeadlinesAdapter.ViewHolder>() {
    val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(old: Article, new: Article): Boolean {
            return old.url == new.url
        }

        override fun areContentsTheSame(old: Article, new: Article): Boolean {
            return old == new
        }
    })

    inner class ViewHolder(val binding: RvTechnologyHeadlinesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvTechnologyHeadlinesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.tvTitle.text = article.title
        holder.binding.tvDescription.text = article.content
        Glide.with(holder.itemView).load(article.urlToImage).into(holder.binding.sivPicture)
    }

    override fun getItemCount(): Int = differ.currentList.size
}