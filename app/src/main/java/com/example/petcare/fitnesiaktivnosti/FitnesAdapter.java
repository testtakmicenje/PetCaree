package com.example.petcare.fitnesiaktivnosti;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petcare.R;
import com.example.petcare.prehrana.Ljubimac;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class FitnesAdapter extends RecyclerView.Adapter<com.example.petcare.fitnesiaktivnosti.FitnesAdapter.ProductViewHolder> {
    int custom_list_item;

    SQLiteDatabase mDatabase;

    private Context context;
    private boolean isToastShown = false;

    private List<Fitnes> myJobsAndMyProjectsListModel;

    public FitnesAdapter(Context context, int custom_list_item, List<Fitnes> myJobsAndMyProjectsListModel, SQLiteDatabase mDatabase) {
        this.context = context;
        this.custom_list_item = custom_list_item;
        this.mDatabase = mDatabase;
        this.myJobsAndMyProjectsListModel = myJobsAndMyProjectsListModel;
        this.isToastShown = false; // Postavi isToastShown na false prilikom kreiranja adaptera
    }

    @NonNull
    @Override
    public com.example.petcare.fitnesiaktivnosti.FitnesAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.prehrana_adapter, null);
        return new com.example.petcare.fitnesiaktivnosti.FitnesAdapter.ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(com.example.petcare.fitnesiaktivnosti.FitnesAdapter.ProductViewHolder holder, int position) {
        final Fitnes workersListModel = myJobsAndMyProjectsListModel.get(position);
        holder.textViewName.setText("Ime: " + workersListModel.getEmail());
        holder.textViewUsername.setText("Vrijeme: " + workersListModel.getVrijeme());

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation");

                builder.setMessage(Html.fromHtml("<font color='#3B3B3B'>Da li si siguran da želiš izbirsati ovu aktivnost?</font>"));

                builder.setPositiveButton("Izbriši", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sql = "DELETE FROM Prehrana2 WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{workersListModel.getId()});
                        removeUserFromFunction( workersListModel); // Izbačaj korisnika iz funkcije
                        notifyDataSetChanged(); // Obavesti adapter da je došlo do promena

                        // Display toast for weight deletion
                        showToast("Aktivnost je obrisana.");
                    }
                });

                builder.setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing, just close the dialog
                    }
                });

                // Prevent dialog dismissal when clicking outside
                builder.setCancelable(false);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                // Customize the colors of the AlertDialog buttons
                int buttonPositiveColor = ContextCompat.getColor(context, R.color.buttonPositiveColor);
                int buttonNegativeColor = ContextCompat.getColor(context, R.color.buttonNegativeColor);

                // Set text color for buttons
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(buttonPositiveColor);
                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(buttonNegativeColor);
            }
        });

    }

    void reloadEmployeesFromDatabase() {
        Cursor cursorproduct1 = mDatabase.rawQuery("SELECT * FROM Prehrana2", null);

        if (cursorproduct1.moveToFirst()) {
            myJobsAndMyProjectsListModel.clear();
            do {
                Fitnes workersListModel = new Fitnes(
                        cursorproduct1.getInt(0),
                        cursorproduct1.getString(1),
                        cursorproduct1.getString(2));

                // Dodajte workersListModel na kraj liste umjesto na početak
                myJobsAndMyProjectsListModel.add( workersListModel);
            } while (cursorproduct1.moveToNext());
        }

        cursorproduct1.close();

        // Obrnite redoslijed liste kako biste dobili najnovije težine na vrhu
        Collections.reverse(myJobsAndMyProjectsListModel);

        notifyDataSetChanged();
    }

    private void removeUserFromFunction(Fitnes workersListModel) {
        myJobsAndMyProjectsListModel.remove(workersListModel);
        notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return myJobsAndMyProjectsListModel.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewUsername, textViewEmail, textViewPhone, startDate;
        ImageView editbtn, deletebtn;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
            startDate = itemView.findViewById(R.id.startdate);
            deletebtn = itemView.findViewById(R.id.buttonDeleteStudent);
            editbtn = itemView.findViewById(R.id.buttonEditstudent);
        }
    }

    // Add this method to display a toast
    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}