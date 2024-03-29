package com.example.petcare.forum.fragments;

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.petcare.databinding.FragmentProfileBinding
import com.example.petcare.forum.EditProfileActivity
import com.example.petcare.forum.adapter.PhotoAdapter
import com.example.petcare.forum.model.Post
import com.example.petcare.forum.model.User
import com.example.petcare.forum.utils.openActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var firebaseUser: FirebaseUser

    private lateinit var photoAdapterPosts: PhotoAdapter

    private lateinit var photoAdapterSaves: PhotoAdapter

    private val posts = mutableListOf<Post>()

    private val saves = mutableListOf<Post>()

    private lateinit var id: String

    @SuppressLint("SetTextI18n")
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?,

    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        val data = requireContext().getSharedPreferences("PROFILE", Context.MODE_PRIVATE)

            .getString("profileId", "none")

        id = if (data == "none") {

            firebaseUser.uid

        } else {

            data!!

        }

        userInfo()

        getFollowerAndFollowingCount()

        getPostCount()

        loadPost()

        loadSaves()

        if (id == firebaseUser.uid) {

            binding.editProfile.text = "uredite profil"

        } else {

            checkFollowingStatus()

        }

        binding.editProfile.setOnClickListener {

            val btnText = binding.editProfile.text.toString().lowercase()

            if (btnText == "uredite profil") {

                context?.openActivity(EditProfileActivity::class.java)

            } else {

                if (btnText == "follow") {

                    FirebaseDatabase.getInstance().reference.child("Follow")

                        .child(firebaseUser.uid).child("following").child(id)

                        .setValue(true)

                    FirebaseDatabase.getInstance().reference.child("Follow")

                        .child(id).child("followers").child(firebaseUser.uid)

                        .setValue(true)

                } else {

                    FirebaseDatabase.getInstance().reference.child("Follow")

                        .child(firebaseUser.uid).child("following").child(id).removeValue()

                    FirebaseDatabase.getInstance().reference.child("Follow")

                        .child(id).child("followers").child(firebaseUser.uid)

                        .removeValue()

                }

            }

        }

        binding.recyclerViewMyPictures.apply {

            layoutManager = GridLayoutManager(context, 3)

            setHasFixedSize(true)

        }

        binding.recyclerViewSavedPictures.apply {

            layoutManager = GridLayoutManager(context, 3)

            setHasFixedSize(true)

        }

        photoAdapterPosts = PhotoAdapter(posts)

        photoAdapterSaves = PhotoAdapter(saves)

        binding.recyclerViewMyPictures.adapter = photoAdapterPosts

        binding.recyclerViewSavedPictures.adapter = photoAdapterSaves

        binding.recyclerViewMyPictures.visibility = View.VISIBLE

        binding.recyclerViewSavedPictures.visibility = View.GONE

        binding.myPictures.setOnClickListener {

            loadPost()

            binding.recyclerViewMyPictures.visibility = View.VISIBLE

            binding.recyclerViewSavedPictures.visibility = View.GONE

        }

        binding.savedPictures.setOnClickListener {

            loadSaves()

            binding.recyclerViewMyPictures.visibility = View.GONE

            binding.recyclerViewSavedPictures.visibility = View.VISIBLE

        }

        return binding.root

    }

    private fun loadSaves() {

        val savedIds = mutableListOf<String>()

        FirebaseDatabase.getInstance().reference.child("Saves").child(id)

            .addValueEventListener(object :

                ValueEventListener {

                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    saves.clear()

                    for (snapshot in dataSnapshot.children) {

                        val id = snapshot.key!!

                        savedIds.add(id)

                    }

                    FirebaseDatabase.getInstance().reference.child("Posts").addValueEventListener(

                        object : ValueEventListener {

                            @SuppressLint("NotifyDataSetChanged")
                            override fun onDataChange(dataSnapshot: DataSnapshot) {

                                for (snapshot in dataSnapshot.children) {

                                    val post = snapshot.getValue(Post::class.java)!!

                                    for (id in savedIds) {

                                        if (post.postid == id) {

                                            saves.add(post)

                                        }

                                    }

                                }

                                photoAdapterSaves.notifyDataSetChanged()

                            }

                            override fun onCancelled(error: DatabaseError) {

                            }

                        })

                    photoAdapterPosts.notifyDataSetChanged()

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }

    private fun loadPost() {

        FirebaseDatabase.getInstance().reference.child("Posts").addValueEventListener(object :

            ValueEventListener {

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                posts.clear()

                for (snapshot in dataSnapshot.children) {

                    val post = snapshot.getValue(Post::class.java)!!

                    if (post.publisher == id) {

                        posts.add(post)

                    }

                }

                posts.reversed()

                photoAdapterPosts.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    private fun checkFollowingStatus() {

        FirebaseDatabase.getInstance().reference.child("Follow")

            .child(firebaseUser.uid).child("following").addValueEventListener(object :

                ValueEventListener {

                @SuppressLint("SetTextI18n")
                override fun onDataChange(snapshot: DataSnapshot) {

                    if (snapshot.child(id).exists()) {

                        binding.editProfile.text = "pratite"

                    } else {

                        binding.editProfile.text = "prati"

                    }

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }

    private fun getPostCount() {

        FirebaseDatabase.getInstance().reference.child("Posts").addValueEventListener(

            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    posts.clear()

                    var totalCount = 0

                    for (s in snapshot.children) {

                        val post = s.getValue(Post::class.java)!!

                        if (post.publisher == id) {

                            totalCount += 1

                        }

                    }

                    binding.posts.text = "$totalCount"

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }

    private fun getFollowerAndFollowingCount() {

        val ref = FirebaseDatabase.getInstance().reference.child("Follow").child(id)

        ref.child("followers").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                binding.followers.text = snapshot.childrenCount.toString()

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        ref.child("following").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                binding.following.text = snapshot.childrenCount.toString()

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    private fun userInfo() {

        FirebaseDatabase.getInstance().reference.child("Users").child(id)

            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        val user = dataSnapshot.getValue(User::class.java)!!

                        Picasso.get().load(user.imageurl).into(binding.profileImage)

                        binding.fullname.text = user.fullname

                        binding.bio.text = user.bio

                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })

    }

}
