package com.example.petcare.forum.adapter;

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.petcare.R
import com.example.petcare.databinding.UserItemBinding
import com.example.petcare.forum.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class UserAdapter(private val users: List<User>) :

    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var firebaseUser: FirebaseUser

    inner class ViewHolder(private val itemBinding: UserItemBinding) :

        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(user: User) {

            itemBinding.apply {

                fullname.text = user.fullname

                username.text = user.username

                btnFollow.visibility = View.VISIBLE

                Log.d("ImageUrl", user.imageurl)

                Picasso.get().load(user.imageurl).placeholder(R.drawable.userprofilepictureplaceholder)

                    .into(imageProfile)

                isFollowed(user.id, btnFollow)

                if (user.id == firebaseUser.uid) {

                    btnFollow.visibility = View.GONE

                }

                btnFollow.setOnClickListener {

                    if (btnFollow.text.toString() == "prati") {

                        FirebaseDatabase.getInstance().reference.child("Follow")

                            .child(firebaseUser.uid).child("following").child(user.id)

                            .setValue(true)

                        FirebaseDatabase.getInstance().reference.child("Follow")

                            .child(user.id).child("followers").child(firebaseUser.uid)

                            .setValue(true)

                        addNotification(user.id)

                    } else {

                        FirebaseDatabase.getInstance().reference.child("Follow")

                            .child(firebaseUser.uid).child("following").child(user.id).removeValue()

                        FirebaseDatabase.getInstance().reference.child("Follow")

                            .child(user.id).child("followers").child(firebaseUser.uid)

                            .removeValue()

                    }

                }

            }

        }

        private fun addNotification(id: String) {

            val map = HashMap<String, Any>()

            map["userid"] = firebaseUser.uid

            map["postid"] = ""

            map["text"] = "počinje te pratiti."

            map["isPost"] = false

            FirebaseDatabase.getInstance().reference.child("Notifications").child(id)
                .push().setValue(map)

        }

        private fun isFollowed(id: String, btnFollow: Button) {

            val reference =

                FirebaseDatabase.getInstance().reference.child("Follow").child(firebaseUser.uid)

                    .child("following")

            reference.addValueEventListener(object : ValueEventListener {

                @SuppressLint("SetTextI18n")
                override fun onDataChange(snapshot: DataSnapshot) {

                    if (snapshot.child(id).exists())

                        btnFollow.text = "pratite"

                    else

                        btnFollow.text = "prati"

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding =

            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        val user = users[position]

        holder.bind(user)

    }

    override fun getItemCount(): Int {

        return users.size

    }

}
