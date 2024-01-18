package com.example.petcare.mojiljubimci;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
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

        // Inicijalizirajte DatabaseHelper ako nije
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

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        final Pet pet = petList.get(holder.getAdapterPosition());

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
                // Prikažite dijalog za potvrdu brisanja
                showDeleteConfirmationDialog(pet, holder.getAdapterPosition());
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

    // Metoda za ažuriranje URI-ja slike za određenog ljubimca
    public void updatePetImage(int position, String imageUri) {
        Pet pet = petList.get(position);
        pet.setImageUri(imageUri);
        notifyItemChanged(position);
    }

    // Metoda za prikaz dijaloga za potvrdu brisanja
    private void showDeleteConfirmationDialog(final Pet pet, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Html.fromHtml("<font color='#3B3B3B'>Potvrda</font>"));
        builder.setMessage(Html.fromHtml("<font color='#3B3B3B'>Da li ste sigurni da želite izbrisati ovog ljubimca?</font>"));

        builder.setPositiveButton("Izbriši", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Pozovite metodu za brisanje ljubimca iz baze podataka
                if (databaseHelper != null) {
                    databaseHelper.deletePet(pet.getId());
                } else {
                    Log.e("MyPetsActivity", "DatabaseHelper is null");
                    return; // Ako je DatabaseHelper null, prekinite izvršavanje
                }

                // Osvježavanje prikaza RecyclerView-a
                petList.remove(position);
                notifyItemRemoved(position);

                // Prikazivanje poruke da je ljubimac izbrisan
                Toast.makeText(context, "Ljubimac je trajno izbrisan.", Toast.LENGTH_SHORT).show();

                // Otvorite MyPetsActivity nakon brisanja

            }
        });

        builder.setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Zatvori dijalog, korisnik je odustao od brisanja
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Prilagodite boje gumba AlertDialog-a
        int buttonPositiveColor = ContextCompat.getColor(context, R.color.buttonPositiveColor);
        int buttonNegativeColor = ContextCompat.getColor(context, R.color.buttonNegativeColor);

        // Postavite boju teksta za gumbe
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(buttonPositiveColor);
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(buttonNegativeColor);
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
