package com.example.petcare.medecinskipodaci;

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
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class MyMedicalInfoAdapter extends RecyclerView.Adapter<MyMedicalInfoAdapter.ProductViewHolder> {
    int custom_list_item;

    SQLiteDatabase mDatabase;

    private Context context;
    private boolean isToastShown = false;

    private List<MyMedicalInfoModel> myJobsAndMyProjectsListModel;

    public MyMedicalInfoAdapter(Context context, int custom_list_item, List<MyMedicalInfoModel> myJobsAndMyProjectsListModel, SQLiteDatabase mDatabase) {
        this.context = context;
        this.custom_list_item = custom_list_item;
        this.mDatabase = mDatabase;
        this.myJobsAndMyProjectsListModel = myJobsAndMyProjectsListModel;
        this.isToastShown = false;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_medicalinfo_adapter, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final MyMedicalInfoModel workersListModel = myJobsAndMyProjectsListModel.get(position);
        holder.textViewName.setText("Datum: " + workersListModel.getDate());
        holder.textViewUsername.setText("Vrsta: " + workersListModel.getUsername());
        holder.textViewEmail.setText("Ime: " + workersListModel.getEmail());
        holder.textViewPhone.setText("Bolest: " + workersListModel.getPhno());
        holder.startDate.setText("Lijek: " + workersListModel.getLijek());

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmployee(workersListModel);
            }
        });

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation");

                builder.setMessage(Html.fromHtml("<font color='#3B3B3B'>Da li si siguran da želiš izbrisati ovaj medicinski podatak?</font>"));

                builder.setPositiveButton("Izbriši", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sql = "DELETE FROM Student2 WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{workersListModel.getId()});
                        removeUserFromFunction(workersListModel); // Izbačaj korisnika iz funkcije
                        notifyDataSetChanged(); // Obavesti adapter da je došlo do promena


                        showToast("Medicinski podatak je obrisan.");
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

    void reloadEmployeesFromDatabase() {
        Cursor cursorproduct1 = mDatabase.rawQuery("SELECT * FROM Student2", null);

        if (cursorproduct1.moveToFirst()) {
            myJobsAndMyProjectsListModel.clear();
            do {
                MyMedicalInfoModel workersListModel = new MyMedicalInfoModel(
                        cursorproduct1.getInt(0),
                        cursorproduct1.getString(1),
                        cursorproduct1.getString(2),
                        cursorproduct1.getString(3),
                        cursorproduct1.getString(4),
                        cursorproduct1.getString(5));


                myJobsAndMyProjectsListModel.add(workersListModel);
            } while (cursorproduct1.moveToNext());
        }

        cursorproduct1.close();


        Collections.reverse(myJobsAndMyProjectsListModel);

        notifyDataSetChanged();
    }

    private void updateEmployee(final MyMedicalInfoModel workersListModel) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_edit_medical_info, null);
        builder.setView(view);

        final DatePicker datePicker = view.findViewById(R.id.dateDatePicker);
        final EditText editUsername = view.findViewById(R.id.workerphonenumber);
        final EditText editemail = view.findViewById(R.id.workeremail);
        final EditText editphno = view.findViewById(R.id.workersalary);
        final EditText editlijek = view.findViewById(R.id.lijek);

        String[] dateParts = workersListModel.getDate().split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]) - 1;
        int day = Integer.parseInt(dateParts[2]);
        datePicker.init(year, month, day, null);

        editUsername.setText(workersListModel.getEmail());
        editemail.setText(workersListModel.getUsername());
        editphno.setText(workersListModel.getPhno());
        editlijek.setText(workersListModel.getLijek());

        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dayOfMonth = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = formatDate(year, month, dayOfMonth);


                String name = workersListModel.getDate();
                String email = editemail.getText().toString().trim();
                String username = workersListModel.getUsername();
                String phno = workersListModel.getPhno();
                String lijek = workersListModel.getLijek();


                if (!editemail.getText().toString().trim().isEmpty()) {
                    email = editemail.getText().toString().trim();
                }

                if (!editphno.getText().toString().trim().isEmpty()) {
                    phno = editphno.getText().toString().trim();
                }

                if (!editUsername.getText().toString().trim().isEmpty()) {
                    username = editUsername.getText().toString().trim();
                }

                if (!editlijek.getText().toString().trim().isEmpty()) {
                    lijek = editlijek.getText().toString().trim();
                }


                String sql = " UPDATE Student2 \n" +
                        " SET Date = ?, \n" +
                        " Email = ?,\n" +
                        " PhoneNO = ?,\n" +
                        " WorkerSalary= ?, \n" +
                        " Lijek= ? \n" +
                        "WHERE id = ?;\n";

                mDatabase.execSQL(sql, new String[]{date, email, username, phno, lijek, String.valueOf(workersListModel.getId())});

                dialog.dismiss();

                ((Activity) context).finish();


                showToast("Medicinski podatak je uređen.");


                reloadEmployeesFromDatabase();
            }
        });

        view.findViewById(R.id.logoImageView1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }



    private void removeUserFromFunction(MyMedicalInfoModel workersListModel) {
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


    private String formatDate(int year, int month, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new java.util.Date(year - 1900, month, day));
    }


    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
