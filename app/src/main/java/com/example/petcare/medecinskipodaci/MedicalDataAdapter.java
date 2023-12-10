package com.example.petcare.medecinskipodaci;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
import java.text.SimpleDateFormat;
import java.util.List;

public class MedicalDataAdapter extends RecyclerView.Adapter<MedicalDataAdapter.ProductViewHolder> {

    int custom_list_item;
    SQLiteDatabase mDatabase;
    private Context context;
    private boolean isToastShown = false;
    private List<MedicalDataModel> medicalDataModelList;

    public MedicalDataAdapter(Context context, int custom_list_item, List<MedicalDataModel> medicalDataModelList, SQLiteDatabase mDatabase) {
        this.context = context;
        this.custom_list_item = custom_list_item;
        this.mDatabase = mDatabase;
        this.medicalDataModelList = medicalDataModelList;
        this.isToastShown = false;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_medical_data, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final MedicalDataModel medicalDataModel = medicalDataModelList.get(position);
        holder.textViewName.setText("Datum: " + medicalDataModel.getDate());
        holder.textViewUsername.setText("Ime ljubimca: " + medicalDataModel.getPetName());
        holder.textViewEmail.setText("Rasa: " + medicalDataModel.getPetBreed());

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMedicalData(medicalDataModel);
            }
        });

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Potvrda");

                builder.setMessage(Html.fromHtml("<font color='#3B3B3B'>Jeste li sigurni da želite izbrisati ove medicinske podatke?</font>"));

                builder.setPositiveButton("Izbriši", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sql = "DELETE FROM MedicalData WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{medicalDataModel.getId()});
                        reloadMedicalDataFromDatabase();
                        ((Activity) context).finish();

                        showToast("Medicinski podaci su izbrisani.");
                    }
                });

                builder.setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.setCancelable(false);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                int buttonPositiveColor = ContextCompat.getColor(context, R.color.buttonPositiveColor);
                int buttonNegativeColor = ContextCompat.getColor(context, R.color.buttonNegativeColor);

                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(buttonPositiveColor);
                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(buttonNegativeColor);
            }
        });
    }

    void reloadMedicalDataFromDatabase() {
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM MedicalData", null);

        if (cursor.moveToFirst()) {
            medicalDataModelList.clear();
            do {
                medicalDataModelList.add(new MedicalDataModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        cursor.close();

        notifyDataSetChanged();
    }

    private void updateMedicalData(final MedicalDataModel medicalDataModel) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.medical_dialog, null);
        builder.setView(view);

        final DatePicker datePicker = view.findViewById(R.id.dateDatePicker);
        final EditText editPetName = view.findViewById(R.id.workeremail);
        final EditText editPetBreed = view.findViewById(R.id.workerphonenumber);

        String[] dateParts = medicalDataModel.getDate().split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]) - 1;
        int day = Integer.parseInt(dateParts[2]);
        datePicker.init(year, month, day, null);

        editPetName.setText(medicalDataModel.getPetName());
        editPetBreed.setText(medicalDataModel.getPetBreed());

        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dayOfMonth = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = formatDate(year, month, dayOfMonth);

                String petName = editPetName.getText().toString().trim();
                String petBreed = editPetBreed.getText().toString().trim();

                String sql = " UPDATE MedicalData \n" +
                        " SET Date = ?, \n" +
                        " PetName = ?,\n" +
                        " PetBreed = ? \n" +
                        "WHERE id = ?;\n";

                mDatabase.execSQL(sql, new String[]{date, petName, petBreed, String.valueOf(medicalDataModel.getId())});

                dialog.dismiss();
                ((Activity) context).finish();

                showToast("Medicinski podaci su ažurirani.");
            }
        });

        view.findViewById(R.id.logoImageView1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void removeMedicalData(MedicalDataModel medicalDataModel) {
        medicalDataModelList.remove(medicalDataModel);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return medicalDataModelList.size();
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

    private String formatDate(int year, int month, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new java.util.Date(year - 1900, month, day));
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).
                show();
    }
}
