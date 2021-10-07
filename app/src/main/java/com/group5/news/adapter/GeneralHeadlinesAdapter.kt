package com.group5.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.group5.news.data.Article
import com.group5.news.databinding.RvGeneralHeadlinesBinding

class GeneralHeadlinesAdapter : RecyclerView.Adapter<GeneralHeadlinesAdapter.ViewHolder>() {
    val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(old: Article, new: Article): Boolean {
            return old.url == new.url
        }

        override fun areContentsTheSame(old: Article, new: Article): Boolean {
            return old == new
        }
    })

    inner class ViewHolder(val binding: RvGeneralHeadlinesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvGeneralHeadlinesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.tvTitle.text = article.title
        holder.binding.tvDate.text = article.publishedAt
        Glide.with(holder.itemView).load(article.urlToImage).into(holder.binding.sivPicture)
    }

    override fun getItemCount(): Int = differ.currentList.size
}