package com.example.petcare.forum;

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcare.R
import com.example.petcare.databinding.ActivityCommentBinding
import com.example.petcare.forum.adapter.CommentAdapter
import com.example.petcare.forum.model.Comment
import com.example.petcare.forum.model.User
import com.example.petcare.forum.utils.createShortToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class CommentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentBinding

    private var postId = ""

    private var authorId = ""

    private val comments = mutableListOf<Comment>()

    private lateinit var adapter: CommentAdapter

    private lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")

        window.setFlags(

            WindowManager.LayoutParams.FLAG_FULLSCREEN,

            WindowManager.LayoutParams.FLAG_FULLSCREEN

        )

        binding = ActivityCommentBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        binding.apply {

            setSupportActionBar(toolbar)

            supportActionBar?.title = "Komentari"

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            toolbar.setNavigationOnClickListener {

                finish()

            }

            postId = intent.getStringExtra("postId")!!

            authorId = intent.getStringExtra("authorId")!!

            getUserImage()

            post.setOnClickListener {

                if (TextUtils.isEmpty(addComment.text.toString())) {

                    createShortToast("Nijedan komentar nije dodan!")

                } else {

                    putComment()

                }

            }

        }

        binding.recyclerViewComments.apply {

            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(context)

        }

        adapter = CommentAdapter(comments, postId)

        binding.recyclerViewComments.adapter = adapter

        loadComments()

    }

    private fun loadComments() {

        FirebaseDatabase.getInstance().reference.child("Comments").child(postId)

            .addValueEventListener(

                object : ValueEventListener {

                    @SuppressLint("NotifyDataSetChanged")
                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        comments.clear()

                        for (snapshot in dataSnapshot.children) {

                            val comment: Comment = snapshot.getValue(Comment::class.java)!!

                            comments.add(comment)

                        }

                        Log.d("Comments Tag", comments.toString())

                        adapter.notifyDataSetChanged()

                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })

    }

    private fun putComment() {

        val map = HashMap<String, Any>()

        val ref = FirebaseDatabase.getInstance().reference.child("Comments").child(postId)

        val id = ref.push().key!!

        map["id"] = id

        map["comment"] = binding.addComment.text.toString()

        map["publisher"] = firebaseUser.uid

        binding.addComment.setText("")

        ref.child(id)

            .setValue(map).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    createShortToast("Komentar dodan!")

                } else {

                    createShortToast(task.exception?.message!!)

                }

            }

    }

    private fun getUserImage() {

        FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser.uid)

            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        val user = dataSnapshot.getValue(User::class.java)

                        if (user?.imageurl == "default") {

                            binding.profileImage.setImageResource(R.drawable.userprofilepictureplaceholder)

                        } else {

                            Picasso.get().load(user?.imageurl).into(binding.profileImage)

                        }

                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })

    }

}
