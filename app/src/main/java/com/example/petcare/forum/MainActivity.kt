package com.example.petcare.forum;

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.petcare.R
import com.example.petcare.databinding.ActivityMainForumBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainForumBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")

        window.setFlags(

            WindowManager.LayoutParams.FLAG_FULLSCREEN,

            WindowManager.LayoutParams.FLAG_FULLSCREEN

        )

        binding = ActivityMainForumBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setMyAccountId()

        val navController = Navigation.findNavController(this, R.id.myNavHostFragment)

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        val bundle: Bundle? = intent.extras

        if (bundle != null) {

            val id = intent.getStringExtra("publisher")

            getSharedPreferences("PROFILE", MODE_PRIVATE).edit().putString("id", id)

                .apply()

        }

    }

    private fun setMyAccountId() {

        val editor = getSharedPreferences("PREFS", MODE_PRIVATE).edit()

        editor.putString("profileid", FirebaseAuth.getInstance().currentUser!!.uid)

        editor.apply()

    }

}
