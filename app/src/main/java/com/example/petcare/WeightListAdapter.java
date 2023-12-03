package com.example.petcare;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class WeightListAdapter extends ArrayAdapter<WeightEntry> {

    private Context context;
    private int resource;
    private List<WeightEntry> weightList;
    private DatabaseHelper dbHelper;

    public WeightListAdapter(Context context, int resource, List<WeightEntry> weightList, DatabaseHelper dbHelper) {
        super(context, resource, weightList);
        this.context = context;
        this.resource = resource;
        this.weightList = weightList;
        this.dbHelper = dbHelper; // Inicijalizacija dbHelper-a
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(resource, parent, false);

            holder = new ViewHolder();
            holder.dateTextView = row.findViewById(R.id.dateTextView);
            holder.petTypeTextView = row.findViewById(R.id.petTypeTextView);
            holder.petNameTextView = row.findViewById(R.id.petNameTextView);
            holder.weightTextView = row.findViewById(R.id.weightTextView);
            holder.editIcon = row.findViewById(R.id.editIcon);
            holder.deleteIcon = row.findViewById(R.id.deleteIcon);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        final WeightEntry weightEntry = weightList.get(position);

        // Samo prikaži stavke koje nisu označene za brisanje
        if (!weightEntry.isMarkedForDeletion()) {
            holder.dateTextView.setText("Datum: " + weightEntry.getDate());
            holder.petTypeTextView.setText("Vrsta ljubimca: " + weightEntry.getPetType());
            holder.petNameTextView.setText("Ime ljubimca: " + weightEntry.getPetName());
            holder.weightTextView.setText("Težina: " + weightEntry.getWeight() + " kg");

            // ... ostatak koda za ostale elemente UI-a

            holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Implementacija funkcionalnosti za trajno brisanje
                    if (weightEntry != null) {
                        dbHelper.deleteWeight(weightEntry.getId()); // Brisanje iz baze podataka
                        weightList.remove(position); // Uklanjanje iz liste
                        notifyDataSetChanged();
                        Toast.makeText(context, "Težina je trajno izbrisana", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // Unutar metode onClick u WeightListAdapter
            holder.editIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WeightEntry weightEntry = weightList.get(position);

                    // Kreirajte Intent

                }
            });
        } else {
            // Ako je označena za brisanje, sakrij redak
            row.setVisibility(View.GONE);
        }

        return row;
    }

    private static class ViewHolder {
        TextView dateTextView;
        TextView petTypeTextView;
        TextView petNameTextView;
        TextView weightTextView;
        ImageView editIcon;
        ImageView deleteIcon;
    }
}


