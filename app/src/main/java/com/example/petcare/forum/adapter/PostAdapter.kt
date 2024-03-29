package com.example.petcare.forum.adapter;

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.petcare.R
import com.example.petcare.databinding.PostItemBinding
import com.example.petcare.forum.CommentActivity
import com.example.petcare.forum.model.Post
import com.example.petcare.forum.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class PostAdapter(

        private var posts: List<Post>,

        val firebaseUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!,

        ) :

    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemBinding: PostItemBinding) :

        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(post: Post) {

            itemBinding.apply {

                Picasso.get().load(post.imageurl).into(postImage)

                description.text = post.description

                FirebaseDatabase.getInstance().reference.child("Users").child(post.publisher)

                    .addValueEventListener(

                         object : ValueEventListener {

                            override fun onDataChange(dataSnapshot: DataSnapshot) {

                                val user = dataSnapshot.getValue(User::class.java)

                                if (user?.imageurl == "default") {

                                    profileImage.setImageResource(R.drawable.userprofilepictureplaceholder)

                                } else {

                                    Picasso.get().load(user?.imageurl)

                                        .placeholder(R.drawable.userprofilepictureplaceholder)

                                        .into(profileImage)

                                }

                                username.text = user?.username

                                author.text = user?.fullname

                            }

                            override fun onCancelled(error: DatabaseError) {

                            }

                        })

                isLiked(post.postid, like)

                noOfLikes(post.postid, noOfLikes)

                getComments(post.postid, noOfComments)

                isSaved(post.postid, itemBinding.save)

                like.setOnClickListener {

                    if (like.tag == "like") {

                        FirebaseDatabase.getInstance().reference.child("Likes").child(post.postid)

                            .child(firebaseUser.uid).setValue(true)

                        addNotification(post.postid, firebaseUser.uid)

                    } else {

                        FirebaseDatabase.getInstance().reference.child("Likes").child(post.postid)

                            .child(firebaseUser.uid).removeValue()

                    }

                }

                comment.setOnClickListener {

                    showComments(post)

                }

                noOfComments.setOnClickListener {

                    showComments(post)

                }

                save.setOnClickListener {

                    if (save.tag == "save") {

                        FirebaseDatabase.getInstance().reference.child("Saves")

                            .child(firebaseUser.uid)

                            .child(post.postid).setValue(true)

                    } else {

                        FirebaseDatabase.getInstance().reference.child("Saves")

                            .child(firebaseUser.uid)

                            .child(post.postid).removeValue()

                    }

                }

                username.setOnClickListener { view ->

                    itemBinding.root.context.getSharedPreferences("PROFILE", Context.MODE_PRIVATE)

                        .edit()

                        .putString("profileId", post.publisher).apply()

                    view.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)

                }

                profileImage.setOnClickListener { view ->

                    itemBinding.root.context.getSharedPreferences("PROFILE", Context.MODE_PRIVATE).edit()

                        .putString("profileId", post.publisher).apply()

                    view.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)

                }
                author.setOnClickListener { view ->

                    itemBinding.root.context.getSharedPreferences("PROFILE", Context.MODE_PRIVATE)

                        .edit()

                        .putString("profileId", post.publisher).apply()

                    view.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)

                }

                postImage.setOnClickListener { view ->

                    itemBinding.root.context.getSharedPreferences("PREFS", Context.MODE_PRIVATE)

                        .edit()

                        .putString("postid", post.postid).apply()

                    if (posts.size > 1)

                        view.findNavController()

                            .navigate(R.id.action_homeFragment_to_postDetailFragment)

                }

            }

        }

        private fun addNotification(postId: String, publisherId: String) {

            val map = HashMap<String, Any>()

            map["userid"] = publisherId

            map["postid"] = postId

            map["text"] = "svidjela mu se objava."

            map["isPost"] = true

            FirebaseDatabase.getInstance().reference.child("Notifications").child(publisherId)

                .push().setValue(map)

        }

        private fun isSaved(postId: String, saveImageView: ImageView) {

            FirebaseDatabase.getInstance().reference.child("Saves").child(firebaseUser.uid)

                .addValueEventListener(

                    object : ValueEventListener {

                        override fun onDataChange(snapshot: DataSnapshot) {

                            if (snapshot.child(postId).exists()) {

                                itemBinding.save.setImageResource(R.drawable.savedicon)

                                saveImageView.tag = "saved"

                            } else {

                                itemBinding.save.setImageResource(R.drawable.saveicon)

                                saveImageView.tag = "save"

                            }

                        }

                        override fun onCancelled(error: DatabaseError) {

                        }

                    })

        }

        private fun showComments(post: Post) {

            val context = itemBinding.root.context

            val intent = Intent(context, CommentActivity::class.java)

            intent.putExtra("postId", post.postid)

            intent.putExtra("authorId", post.publisher)

            context.startActivity(intent)

        }

        private fun getComments(postId: String, textView: TextView) {

            FirebaseDatabase.getInstance().reference.child("Comments").child(postId)

                .addValueEventListener(

                    object : ValueEventListener {

                        @SuppressLint("SetTextI18n")
                        override fun onDataChange(snapshot: DataSnapshot) {

                            textView.text = "Pogledaj sve ${snapshot.childrenCount} komentare."

                        }

                        override fun onCancelled(error: DatabaseError) {

                        }

                    })

        }

        private fun isLiked(postId: String, imageView: ImageView) {

            FirebaseDatabase.getInstance().reference.child("Likes").child(postId)

                .addValueEventListener(

                    object : ValueEventListener {

                        override fun onDataChange(dataSnapshot: DataSnapshot) {

                            if (dataSnapshot.child(firebaseUser.uid).exists()) {

                                imageView.setImageResource(R.drawable.likefilledicon)

                                imageView.tag = "liked"

                            } else {

                                imageView.setImageResource(R.drawable.likoutlineicon)

                                imageView.tag = "like"

                            }

                        }

                        override fun onCancelled(error: DatabaseError) {

                        }

                    })
        }

        private fun noOfLikes(postId: String, textView: TextView) {

            FirebaseDatabase.getInstance().reference.child("Likes").child(postId)

                .addValueEventListener(object : ValueEventListener {

                    @SuppressLint("SetTextI18n")
                    override fun onDataChange(snapshot: DataSnapshot) {

                        val likes = snapshot.childrenCount

                        textView.text = "$likes svidjanja."

                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding =

            PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(posts[position])

    }

    override fun getItemCount(): Int = posts.size

}
