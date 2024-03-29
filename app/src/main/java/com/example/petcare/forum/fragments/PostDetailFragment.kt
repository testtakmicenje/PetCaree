package com.example.petcare.forum.fragments;

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcare.databinding.FragmentPostDetailBinding
import com.example.petcare.forum.adapter.PostAdapter
import com.example.petcare.forum.model.Post
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PostDetailFragment : Fragment() {

    private lateinit var binding: FragmentPostDetailBinding

    private lateinit var postId: String

    private val posts = mutableListOf<Post>()

    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?,

    ): View {

        binding = FragmentPostDetailBinding.inflate(inflater, container, false)

        postId = context?.getSharedPreferences("PREFS", Context.MODE_PRIVATE)

            ?.getString("postid", "none")!!

        binding.recyclerView.apply {

            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(context)

        }

        postAdapter = PostAdapter(posts)

        binding.recyclerView.adapter = postAdapter

        FirebaseDatabase.getInstance().reference.child("Posts").child(postId).addValueEventListener(

            object : ValueEventListener {

                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    posts.clear()

                    posts.add(dataSnapshot.getValue(Post::class.java)!!)

                    postAdapter.notifyDataSetChanged()

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        return binding.root

    }

}
