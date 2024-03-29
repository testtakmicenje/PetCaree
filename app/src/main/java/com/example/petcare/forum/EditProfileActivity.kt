package com.example.petcare.forum;

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.example.petcare.R
import com.example.petcare.databinding.ActivityEditProfileForumBinding
import com.example.petcare.forum.model.User
import com.example.petcare.forum.utils.createProgressBar
import com.example.petcare.forum.utils.createShortToast
import com.example.petcare.forum.utils.openActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso

class EditProfileActivity : AppCompatActivity() {

    private lateinit var firebaseUser: FirebaseUser

    private var imageUri: Uri? = null

    private lateinit var binding: ActivityEditProfileForumBinding

    private lateinit var uploadTask: UploadTask

    private lateinit var storageRef: StorageReference

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->

        if (result.isSuccessful) {

            imageUri = result.uriContent

            binding.profileImage.setImageURI(imageUri)

            uploadImage()

        } else {

            createShortToast("Nešto je pošlo po zlu!")

            openActivity(EditProfileActivity::class.java)

            finish()

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")

        window.setFlags(

            WindowManager.LayoutParams.FLAG_FULLSCREEN,

            WindowManager.LayoutParams.FLAG_FULLSCREEN

        )

        binding = ActivityEditProfileForumBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        storageRef = FirebaseStorage.getInstance().reference.child("Uploads")

        FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser.uid)

            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                        val user = snapshot.getValue(User::class.java)!!

                        binding.apply {

                            fullname.setText(user.fullname)

                            username.setText(user.username)

                            bio.setText(user.bio)

                            Picasso.get().load(user.imageurl).placeholder(R.drawable.userprofilepictureplaceholder).into(profileImage)

                        }

                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })

        binding.close.setOnClickListener {

            finish()

        }

        binding.changePhoto.setOnClickListener {

            cropImage.launch(

                options {

                    setGuidelines(CropImageView.Guidelines.ON)

                    setCropShape(CropImageView.CropShape.OVAL)

                    setOutputCompressFormat(Bitmap.CompressFormat.PNG)

                }

            )

        }

        binding.profileImage.setOnClickListener {

            cropImage.launch(

                options {

                    setGuidelines(CropImageView.Guidelines.ON)

                    setCropShape(CropImageView.CropShape.OVAL)

                    setOutputCompressFormat(Bitmap.CompressFormat.PNG)

                }

            )

        }

        binding.save.setOnClickListener {

            updateProfile()

            finish()

        }

    }

    private fun updateProfile() {

        val map = HashMap<String, Any>()

        val ref = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.uid)

        map["name"] = binding.fullname.text.toString()

        map["username"] = binding.username.text.toString()

        map["bio"] = binding.bio.text.toString()

        ref.updateChildren(map)

        createShortToast("Ažuriranje profila uspješno!")

    }

    private fun uploadImage() {

        val progressBar = createProgressBar(this, binding.ll)

        progressBar.visibility = View.VISIBLE

        if (imageUri != null) {

            val fileRef =

                storageRef.child(System.currentTimeMillis().toString() + ".jpeg")

            uploadTask = fileRef.putFile(imageUri!!)

            uploadTask.continueWithTask { task ->

                if (!task.isSuccessful) {

                    task.exception?.let {

                        throw it

                    }

                }

                fileRef.downloadUrl

            }.addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val downloadUri = task.result

                    val imageUrl = downloadUri.toString()

                    FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser.uid)

                        .child("imageurl").setValue(imageUrl)

                    progressBar.visibility = View.GONE

                } else {

                    createShortToast("Prijenos nije uspio!")

                }

            }.addOnFailureListener { e ->

                createShortToast(e.message!!)

            }

        } else {

            createShortToast("Nijedna slika nije odabrana")

        }

    }

}
