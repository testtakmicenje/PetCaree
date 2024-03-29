package com.example.petcare.forum.fragments;

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcare.databinding.FragmentHomeForumBinding
import com.example.petcare.forum.adapter.PostAdapter
import com.example.petcare.forum.model.Post
import com.example.petcare.početna.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeForumBinding

    private val posts = mutableListOf<Post>()

    private val followingList = mutableListOf<String>()

    private lateinit var adapter: PostAdapter

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?,

    ): View {

        binding = FragmentHomeForumBinding.inflate(inflater, container, false)

        binding.backbutton.setOnClickListener {

            val intent = Intent(activity, HomeActivity::class.java)

            startActivity(intent)

        }

        val linearLayoutManager = LinearLayoutManager(context)

        linearLayoutManager.apply {

            stackFromEnd = true

            reverseLayout = true

        }

        binding.recyclerViewPosts.apply {

            setHasFixedSize(true)

            layoutManager = linearLayoutManager

        }

        adapter = PostAdapter(posts)

        binding.recyclerViewPosts.adapter = adapter

        checkFollowingUsers()

        return binding.root

    }

    private fun checkFollowingUsers() {

        FirebaseDatabase.getInstance().reference.child("Follow")

            .child(FirebaseAuth.getInstance().currentUser?.uid!!).child("following")

            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    followingList.clear()

                    for (snapshot in dataSnapshot.children) {

                        followingList.add(snapshot.key!!)

                    }

                    followingList.add(FirebaseAuth.getInstance().currentUser?.uid!!)

                    readPosts()

                    Log.d("Following Tag", followingList.toString())

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }

    private fun readPosts() {

        FirebaseDatabase.getInstance().reference.child("Posts").addValueEventListener(object :

            ValueEventListener {

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                posts.clear()

                for (snapshot in dataSnapshot.children) {

                    val post: Post = snapshot.getValue(Post::class.java)!!

                    for (id in followingList) {

                        if (post.publisher == id) {

                            posts.add(post)

                        }

                    }

                }

                Log.d("Posts Tag", posts.toString())

                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

}
