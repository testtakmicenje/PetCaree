package com.example.petcare.weight;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.Html;
import android.widget.Toast;
import java.util.Collections;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class MyJobsAndMyProjectsAdapter extends RecyclerView.Adapter<MyJobsAndMyProjectsAdapter.ProductViewHolder> {

    int custom_list_item;

    SQLiteDatabase mDatabase;

    private Context context;
    private boolean isToastShown = false;

    private List<MyJobsAndMyProjectsModel> myJobsAndMyProjectsListModel;

    public MyJobsAndMyProjectsAdapter(Context context, int custom_list_item, List<MyJobsAndMyProjectsModel> myJobsAndMyProjectsListModel, SQLiteDatabase mDatabase) {
        this.context = context;
        this.custom_list_item = custom_list_item;
        this.mDatabase = mDatabase;
        this.myJobsAndMyProjectsListModel = myJobsAndMyProjectsListModel;
        this.isToastShown = false; // Postavi isToastShown na false prilikom kreiranja adaptera
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_jobs_and_my_projects_list_item, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final MyJobsAndMyProjectsModel workersListModel = myJobsAndMyProjectsListModel.get(position);
        holder.textViewName.setText("Datum: " + workersListModel.getName());
        holder.textViewUsername.setText("Vrsta: " + workersListModel.getUsername());
        holder.textViewEmail.setText("Ime: " + workersListModel.getEmail());
        holder.textViewPhone.setText("Težina: " + workersListModel.getPhno());

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateJobAndProject(workersListModel);
            }
        });

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation");

                builder.setMessage(Html.fromHtml("<font color='#3B3B3B'>Da li si siguran da želiš izbrisati ovu težinu?</font>"));

                builder.setPositiveButton("Izbriši", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sql = "DELETE FROM Student WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{workersListModel.getId()});
                        removeUserFromFunction(workersListModel); // Izbačaj korisnika iz funkcije
                        notifyDataSetChanged(); // Obavesti adapter da je došlo do promena

                        // Display toast for weight deletion
                        showToast("Težina je obrisana.");
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
        Cursor cursorproduct1 = mDatabase.rawQuery("SELECT * FROM Student", null);

        if (cursorproduct1.moveToFirst()) {
            myJobsAndMyProjectsListModel.clear();
            do {
                MyJobsAndMyProjectsModel workersListModel = new MyJobsAndMyProjectsModel(
                        cursorproduct1.getInt(0),
                        cursorproduct1.getString(1),
                        cursorproduct1.getString(2),
                        cursorproduct1.getString(3),
                        cursorproduct1.getString(4));

                // Dodajte workersListModel na kraj liste umjesto na početak
                myJobsAndMyProjectsListModel.add(workersListModel);
            } while (cursorproduct1.moveToNext());
        }

        cursorproduct1.close();

        // Obrnite redoslijed liste kako biste dobili najnovije težine na vrhu
        Collections.reverse(myJobsAndMyProjectsListModel);

        notifyDataSetChanged();
    }

    private void updateJobAndProject(final MyJobsAndMyProjectsModel workersListModel) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_edit_my_job_and_my_project, null);
        builder.setView(view);

        final DatePicker datePicker = view.findViewById(R.id.dateDatePicker);
        final EditText editUsername = view.findViewById(R.id.workeremail);
        final EditText editemail = view.findViewById(R.id.workerphonenumber);
        final EditText editphno = view.findViewById(R.id.workersalary);

        String[] dateParts = workersListModel.getName().split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]) - 1;
        int day = Integer.parseInt(dateParts[2]);
        datePicker.init(year, month, day, null);

        editUsername.setText(workersListModel.getUsername());
        editemail.setText(workersListModel.getEmail());
        editphno.setText(workersListModel.getPhno());

        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dayOfMonth = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = formatDate(year, month, dayOfMonth);

                // Čuvanje nepromijenjenih podataka
                String name = workersListModel.getName();
                String email = workersListModel.getEmail();
                String username = workersListModel.getUsername();
                String phno = workersListModel.getPhno();

                // Ako je korisnik unio nove podatke, zamijeni ih
                if (!editemail.getText().toString().trim().isEmpty()) {
                    email = editemail.getText().toString().trim();
                }
                if (!editphno.getText().toString().trim().isEmpty()) {
                    phno = editphno.getText().toString().trim();
                }

                // Ažuriraj bazu podataka
                String sql = " UPDATE Student \n" +
                        " SET Name = ?, \n" +
                        " Email = ?,\n" +
                        " PhoneNO = ?,\n" +
                        " WorkerSalary= ? \n" +
                        "WHERE id = ?;\n";

                mDatabase.execSQL(sql, new String[]{name, email, username, phno, String.valueOf(workersListModel.getId())});

                dialog.dismiss();
                ((Activity) context).finish();

                // Prikazuje toast za ažuriranje težine
                showToast("Težina je uređena.");

                // Ponovno učitaj podatke iz baze podataka
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

    private void removeUserFromFunction(MyJobsAndMyProjectsModel workersListModel) {
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

    // Dodata metoda za formatiranje datuma
    private String formatDate(int year, int month, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new java.util.Date(year - 1900, month, day));
    }

    // Add this method to display a toast
    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}




