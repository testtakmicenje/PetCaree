package com.example.petcare.mojiljubimci;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.petcare.R;
import com.squareup.picasso.Picasso;

public class AddPetActivity extends AppCompatActivity {

    private EditText petNameEditText, petTypeEditText;
    private ImageView petImageView;
    private int positionOfSelectedItem = -1;
    private TextView addPetButton;
    private TextView selectImageButton;
    private EditText petsNoteEditText;
    private Toolbar toolbar;
    private String petImagePath;

    private static final int PICK_IMAGE_REQUEST = 1;

    private DatabaseHelper databaseHelper;
    private PetListAdapter petListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moji_ljubimci);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);



        petNameEditText = findViewById(R.id.petNameEditText);
        petTypeEditText = findViewById(R.id.petTypeEditText);
        petImageView = findViewById(R.id.petImageView);
        addPetButton = findViewById(R.id.add);
        selectImageButton = findViewById(R.id.selectImageButton);
        petsNoteEditText = findViewById(R.id.petsNoteEditText);

        databaseHelper = new DatabaseHelper(this);
        petListAdapter = new PetListAdapter(this, databaseHelper.getAllPets());

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        addPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String petName = petNameEditText.getText().toString();
                String petType = petTypeEditText.getText().toString();
                String petNote = petsNoteEditText.getText().toString();

                long result = databaseHelper.addPet(petName, petType, petImagePath, petNote);

                if (result != -1) {
                    Uri petImageUri = getImageUriFromPetImageView();
                    Pet newPet = new Pet(petName, petType, petNote);

                    newPet.setNote(petNote);
                    newPet.setImagePath(petImageUri.toString());
                    if (positionOfSelectedItem != -1) {
                        petListAdapter.setPetImagePath(positionOfSelectedItem, petImagePath);
                    } else {
                        petListAdapter.addPet(newPet);
                    }

                    petListAdapter.notifyDataSetChanged();

                    Toast.makeText(AddPetActivity.this, "Ljubimac dodan!", Toast.LENGTH_SHORT).show();

                    finish();

                    Intent intent = new Intent(AddPetActivity.this, MyPetsActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(AddPetActivity.this, "Greška pri dodavanju ljubimca!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();


            petImagePath = imageUri.toString();

            if (positionOfSelectedItem != -1) {
                petListAdapter.setPetImagePath(positionOfSelectedItem, petImagePath);
            }

            Picasso.get().load(imageUri).into(petImageView);
        }
    }

    private Uri getImageUriFromPetImageView() {
        Drawable drawable = petImageView.getDrawable();

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();

            if (bitmap != null) {
                if (petImagePath != null) {
                    return Uri.parse(petImagePath);
                } else {
                    return saveImageToGallery(bitmap);
                }
            }
        }

        return null;
    }

    private Uri saveImageToGallery(Bitmap bitmap) {
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Image Description", null);
        return Uri.parse(path);
    }

    private void setCurrentPetPosition(int position) {
        positionOfSelectedItem = position;
    }
}
