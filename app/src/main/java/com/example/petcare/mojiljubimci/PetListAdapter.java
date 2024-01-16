package com.example.petcare.mojiljubimci;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.PetViewHolder> {

    private Context context;
    private List<Pet> petList;

    public PetListAdapter(Context context, List<Pet> petList) {
        this.context = context;
        this.petList = petList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pets_list_item, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        final Pet pet = petList.get(position);

        if (pet.getImageUri() != null) {
            Picasso.get().load(Uri.parse(pet.getImageUri())).into(holder.petImageView);
        } else {
            holder.petImageView.setImageResource(R.drawable.placeholder_image);
        }

        holder.petNameTextView.setText("Ime: " + pet.getName());
        holder.petTypeTextView.setText("Vrsta: " + pet.getType());
        holder.petNoteTextView.setText("Napomena: " + pet.getNote());

        holder.buttonDeletePetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Postavljanje URI-ja slike na null
                pet.setImageUri(null);
                // Osvježavanje prikaza RecyclerView-a
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public void addPet(Pet pet) {
        petList.add(0, pet); // Dodaj na početak liste
        notifyItemInserted(0); // Obavijesti adapter o promjenama
    }

    // Method to update image URI for a specific pet
    public void updatePetImage(int position, String imageUri) {
        Pet pet = petList.get(position);
        pet.setImageUri(imageUri);
        notifyItemChanged(position);
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
