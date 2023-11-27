package com.example.petcare;
// WeightListAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
    public View getView(int position, View convertView, ViewGroup parent) {
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

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        WeightEntry weightEntry = weightList.get(position);
        holder.dateTextView.setText("Datum: " + weightEntry.getDate());
        holder.petTypeTextView.setText("Vrsta ljubimca: " + weightEntry.getPetType());
        holder.petNameTextView.setText("Ime ljubimca: " + weightEntry.getPetName());
        holder.weightTextView.setText("Težina: " + weightEntry.getWeight() + " kg");

        return row;
    }

    private static class ViewHolder {
        TextView dateTextView;
        TextView petTypeTextView;
        TextView petNameTextView;
        TextView weightTextView;
    }
}
