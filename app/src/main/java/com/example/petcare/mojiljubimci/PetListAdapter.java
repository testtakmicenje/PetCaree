package com.example.petcare.mojiljubimci;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.PetViewHolder> {

    private Context context;
    private List<Pet> petList;
    private DatabaseHelper databaseHelper;

    public PetListAdapter(Context context, List<Pet> petList) {
        this.context = context;
        this.petList = petList;


        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context);
        }
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pets_list_item, parent, false);
        return new PetViewHolder(view);
    }

    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        final Pet pet = petList.get(holder.getAdapterPosition());


        Uri imageUri = getImageUriFromDrawable(holder.petImageView.getDrawable(), pet);

        if (imageUri != null) {

            Picasso.get().load(imageUri).into(holder.petImageView);
        } else {

            holder.petImageView.setImageResource(R.drawable.placeholder_image);
        }

        holder.petNameTextView.setText("Ime: " + pet.getName());
        holder.petTypeTextView.setText("Vrsta: " + pet.getType());
        holder.petNoteTextView.setText("Napomena: " + pet.getNote());

        holder.buttonDeletePetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDeleteConfirmationDialog(pet, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public void addPet(Pet pet) {
        petList.add(0, pet);
        notifyItemInserted(0);
    }


    public void setPetImagePath(int position, String imagePath) {
        Pet pet = petList.get(position);
        pet.setImagePath(imagePath);
        notifyItemChanged(position);
    }



    private void showDeleteConfirmationDialog(final Pet pet, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Html.fromHtml("<font color='#3B3B3B'>Potvrda</font>"));
        builder.setMessage(Html.fromHtml("<font color='#3B3B3B'>Da li ste sigurni da želite izbrisati ovog ljubimca?</font>"));

        builder.setPositiveButton("Izbriši", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (databaseHelper != null) {
                    databaseHelper.deletePet(pet.getId());
                } else {
                    Log.e("MyPetsActivity", "DatabaseHelper is null");
                    return;
                }


                petList.remove(position);
                notifyItemRemoved(position);


                Toast.makeText(context, "Ljubimac je trajno izbrisan.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


        int buttonPositiveColor = ContextCompat.getColor(context, R.color.buttonPositiveColor);
        int buttonNegativeColor = ContextCompat.getColor(context, R.color.buttonNegativeColor);


        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(buttonPositiveColor);
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(buttonNegativeColor);
    }

    private Uri getImageUriFromDrawable(Drawable drawable, Pet pet) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();

            if (bitmap != null) {

                if (pet.getImagePath() != null) {
                    return Uri.parse(pet.getImagePath());
                } else {

                    String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Opis slike", null);
                    return Uri.parse(path);
                }
            }
        }


        return null;
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {
        ImageView petImageView;
        TextView petNameTextView;
        TextView petTypeTextView;
        TextView petNoteTextView;
        ImageView buttonDeletePetImage;

        public PetViewHolder(View itemView) {
            super(itemView);
            petImageView = itemView.findViewById(R.id.petImageView);
            petNameTextView = itemView.findViewById(R.id.textViewName);
            petTypeTextView = itemView.findViewById(R.id.textViewUsername);
            petNoteTextView = itemView.findViewById(R.id.textViewNote);
            buttonDeletePetImage = itemView.findViewById(R.id.buttonDeletePetImage);
        }
    }
}
