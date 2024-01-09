package com.example.petcare.skeniranje

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.petcare.databinding.FragmentScanBinding
import com.github.drjacky.imagepicker.ImagePicker

class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null

    private val binding get() = _binding!!

    private var isAdDismissed = false

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?

    ): View {

        _binding = FragmentScanBinding.inflate(inflater, container, false)

        val view = binding.root

        return view

    }


    @SuppressLint("QueryPermissionsNeeded")

    @RequiresApi(Build.VERSION_CODES.P)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        val backImageView: ImageView = binding.logoImageView1
        backImageView.setOnClickListener {
            backImageView.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }

        }

   //     showInterstitial()

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == Activity.RESULT_OK) {

                val mBitmap = it.data?.data!!

                val action = ScanFragmentDirections.actionScanFragmentToResultFragment(mBitmap.toString())

                findNavController().navigate(action)

            }

        }

        binding.btnUpload.setOnClickListener {

           // loadIntAd()

           // showIntAd()

                launcher.launch(

                    activity?.let { it1 ->

                        ImagePicker.with(it1)

                            .galleryOnly()

                            .createIntent()


                    }

                )

        }

        binding.btnCamera.setOnClickListener {

                launcher.launch(

                    activity?.let { it1 ->

                        ImagePicker.with(it1)

                            .cameraOnly()

                            .createIntent()

                    }

                )

            }

        }
}
