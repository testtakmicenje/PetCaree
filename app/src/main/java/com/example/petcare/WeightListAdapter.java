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

    public WeightListAdapter(Context context, int resource, List<WeightEntry> weightList) {
        super(context, resource, weightList);
        this.context = context;
        this.resource = resource;
        this.weightList = weightList;
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
        holder.dateTextView.setText("Datum: " + weightEntry.getDate());
        holder.petTypeTextView.setText("Vrsta ljubimca: " + weightEntry.getPetType());
        holder.petNameTextView.setText("Ime ljubimca: " + weightEntry.getPetName());
        holder.weightTextView.setText("Težina: " + weightEntry.getWeight() + " kg");

        // Dodajte funkcionalnost za uređivanje
        holder.editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementirajte logiku za uređivanje težine
                Toast.makeText(context, "Uredi težinu: " + weightEntry.getWeight(), Toast.LENGTH_SHORT).show();
            }
        });

        // Dodajte funkcionalnost za brisanje
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementirajte logiku za brisanje težine
                weightList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Težina obrisana", Toast.LENGTH_SHORT).show();
            }
        });

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
