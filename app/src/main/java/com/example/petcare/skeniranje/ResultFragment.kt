package com.example.petcare.skeniranje

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.ImageView

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.petcare.R
import com.example.petcare.databinding.FragmentResultBinding
import androidx.appcompat.app.AppCompatActivity



@Suppress("DEPRECATION")
class ResultFragment : Fragment() {

    private var _binding : FragmentResultBinding? = null
    private val binding get() = _binding!!

    private lateinit var mClassifier: Classifier

    private lateinit var mBitmap: Uri

    private lateinit var bitmap : Bitmap

    private var list : ArrayList<String> = ArrayList()

    private lateinit var assetManager : AssetManager

    private val args: ResultFragmentArgs by navArgs()

    private val mInputSize = 224

    private val mModelPath = "model_unquant.tflite"

    private val mLabelPath = "names.txt"

    private var isAdDismissed = false


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?

    ): View {

        _binding = FragmentResultBinding.inflate(inflater, container, false)

        return binding.root

    }

    @SuppressLint("ResourceType", "SetTextI18n")

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




        assetManager = activity?.assets!!

        list = assetManager.open("names.txt").bufferedReader().useLines { it.toList() } as ArrayList<String>

        val assets = activity?.assets

        mClassifier = assets?.let { Classifier(it, mModelPath, mLabelPath, mInputSize) }!!

        mBitmap = args.bitmap.toUri()

        bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, mBitmap)

        bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true)

        binding.scannedImage.setImageBitmap(bitmap)

        if (mClassifier.recognizeImage(bitmap).firstOrNull() == null) {

            binding.diseaseText.text= "Ni jedna bolest nije pronadjena. Pokušajte ponovno i uslikajte jasniju sliku.\n"

        }

        else {

            binding.diseaseText.text =

                "Naziv bolesti: "+ list[mClassifier.recognizeImage(bitmap).firstOrNull()!!.id.toInt()] + "\n Tačnost rezultata: " + mClassifier.recognizeImage(bitmap).firstOrNull()!!.confidence * 100 + " %"

        }

        binding.btnTestAgain.setOnClickListener {

                findNavController().apply {

                    navigate(R.id.action_resultFragment_to_scanFragment)

            }

        }

    }

}
