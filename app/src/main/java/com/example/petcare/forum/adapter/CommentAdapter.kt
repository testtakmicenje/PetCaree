package  com.example.petcare.forum.adapter;

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petcare.R
import com.example.petcare.databinding.CommentItemBinding
import com.example.petcare.forum.MainActivity
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

class CommentAdapter(

        private var comments: List<Comment>,

        private val postId: String,

        val firebaseUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!,

        ) :

    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemBinding: CommentItemBinding) :

        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(comment: Comment) {

            itemBinding.comment.text = comment.comment

            FirebaseDatabase.getInstance().reference.child("Users").child(comment.publisher)

                .addValueEventListener(

                    object : ValueEventListener {

                        override fun onDataChange(snapshot: DataSnapshot) {

                            val user = snapshot.getValue(User::class.java)

                            itemBinding.username.text = user?.username

                            if (user?.imageurl == "default") {

                                itemBinding.profileImage.setImageResource(R.drawable.userprofilepictureplaceholder)

                            } else {

                                Picasso.get().load(user?.imageurl)

                                    .placeholder(R.drawable.userprofilepictureplaceholder)

                                    .into(itemBinding.profileImage)

                            }

                        }

                        override fun onCancelled(error: DatabaseError) {

                        }

                    })

            itemBinding.root.setOnClickListener {

                val intent = Intent(it.context, MainActivity::class.java)

                intent.putExtra("publisher", comment.publisher)

                it.context.startActivity(intent)

            }

            itemBinding.root.setOnLongClickListener {

                if (comment.publisher.endsWith(firebaseUser.uid)) {

                    val dialog = AlertDialog.Builder(itemBinding.root.context).create()

                    dialog.setTitle("Želite li izbrisati?")

                    dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "NE"

                    ) { _, _ -> dialog.dismiss() }

                    dialog.setButton(AlertDialog.BUTTON_POSITIVE, "DA"

                    ) { _, _ ->

                        FirebaseDatabase.getInstance().reference.child("Comments").child(postId)

                            .child(comment.id).removeValue().addOnCompleteListener { task ->

                                if (task.isSuccessful) {

                                    itemBinding.root.context.createShortToast("Comment deleted successfully!")

                                    dialog.dismiss()

                                }

                            }

                    }

                    dialog.show()

                }

                true

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding = CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val comment = comments[position]

        holder.bind(comment)

    }

    override fun getItemCount(): Int = comments.size

}
