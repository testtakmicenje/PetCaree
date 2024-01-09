package com.example.petcare.skeniranje

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.petcare.databinding.ActivityMainScanPlantBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainScanPlantBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        window.setFlags(

            WindowManager.LayoutParams.FLAG_FULLSCREEN,

            WindowManager.LayoutParams.FLAG_FULLSCREEN

        )

        super.onCreate(savedInstanceState)

        binding = ActivityMainScanPlantBinding.inflate(layoutInflater)

        val view =  binding.root

        setContentView(view)

    }

}
