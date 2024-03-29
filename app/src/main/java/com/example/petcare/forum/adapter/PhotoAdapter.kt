package com.example.petcare.forum.adapter;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.petcare.R
import com.example.petcare.databinding.PhotoItemBinding
import com.example.petcare.forum.model.Post
import com.squareup.picasso.Picasso

class PhotoAdapter(private val posts: List<Post>) :

    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemBinding: PhotoItemBinding) :

        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(post: Post) {

            Picasso.get().load(post.imageurl).placeholder(R.drawable.userprofilepictureplaceholder)

                .into(itemBinding.postImage)

            itemBinding.postImage.setOnClickListener { view ->

                itemBinding.root.context.getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("postid", post.postid).apply()

                view.findNavController().navigate(R.id.action_profileFragment_to_postDetailFragment)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(posts[position])

    }

    override fun getItemCount(): Int = posts.size

}
