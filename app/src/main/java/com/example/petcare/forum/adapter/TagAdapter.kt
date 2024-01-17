package com.example.petcare.forum.adapter;

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petcare.databinding.TagItemBinding

class TagAdapter(private var tags: List<String>, private var tagsCount: List<String>) :

    RecyclerView.Adapter<TagAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemBinding: TagItemBinding) :

        RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(tag: String, tagsCount: String) {

            itemBinding.hashTag.text = "# $tag"

            itemBinding.noOfPosts.text = "$tagsCount posts"

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding =

            TagItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(tags[position], tagsCount[position])

    }

    override fun getItemCount(): Int {

        return tags.size.coerceAtLeast(tagsCount.size)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(filterTags: List<String>, filterTagsCount: List<String>) {

        this.tags = filterTags

        this.tagsCount = filterTagsCount

        notifyDataSetChanged()

    }

}
