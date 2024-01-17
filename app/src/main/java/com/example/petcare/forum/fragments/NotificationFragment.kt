package com.example.petcare.forum.fragments;

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcare.databinding.FragmentNotificationBinding
import com.example.petcare.forum.adapter.NotificationAdapter
import com.example.petcare.forum.model.Notification
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding

    private lateinit var adapter: NotificationAdapter

    private var notifications = mutableListOf<Notification>()

    override fun onCreateView(

            inflater: LayoutInflater, container: ViewGroup?,

            savedInstanceState: Bundle?,

            ): View {

        binding = FragmentNotificationBinding.inflate(layoutInflater)

        binding.recyclerView.apply {

            layoutManager = LinearLayoutManager(context)

            setHasFixedSize(true)

        }

        adapter = NotificationAdapter(notifications)

        binding.recyclerView.adapter = adapter

        readNotifications()

        return binding.root

    }

    private fun readNotifications() {

        FirebaseDatabase.getInstance().reference.child("Notifications")

                .child(FirebaseAuth.getInstance().currentUser?.uid!!).addValueEventListener(object :

                        ValueEventListener {

                    @SuppressLint("NotifyDataSetChanged")
                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        notifications.clear()

                        for (snapshot in dataSnapshot.children) {

                            val notification = snapshot.getValue(Notification::class.java)!!

                            notifications.add(notification)

                        }

                        notifications.reverse()

                        Log.d("NotificationFragment", notifications.toString())

                        adapter.notifyDataSetChanged()

                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })

    }

}