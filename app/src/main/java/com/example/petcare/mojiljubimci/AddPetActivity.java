package com.example.petcare.mojiljubimci;

import android.app.Activity;
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

import com.example.petcare.R;

public class AddPetActivity extends Activity {

    private EditText petNameEditText, petTypeEditText;
    private ImageView petImageView;
    private TextView addPetButton;
    private TextView selectImageButton;
    private EditText petsNoteEditText;

    private static final int PICK_IMAGE_REQUEST = 1;

    private DatabaseHelper databaseHelper;
    private PetListAdapter petListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moji_ljubimci);

        petNameEditText = findViewById(R.id.petNameEditText);
        petTypeEditText = findViewById(R.id.petTypeEditText);
        petImageView = findViewById(R.id.petImageView);
        addPetButton = findViewById(R.id.add);
        selectImageButton = findViewById(R.id.selectImageButton);
        petsNoteEditText = findViewById(R.id.petsNoteEditText); // Dodano za napomenu

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

                long result = databaseHelper.addPet(petName, petType, "", petNote);


                if (result != -1) {
                    // Dobijte URI slike i postavite ga u trenutno dodanog ljubimca u adapteru
                    Uri petImageUri = getImageUriFromPetImageView();
                    Pet newPet = new Pet(petName, petType);
                    newPet.setNote(petNote); // Dodano za napomenu
                    newPet.setImageUri(petImageUri.toString());

                    // Dodajte novog ljubimca u listu i obavijestite adapter
                    petListAdapter.addPet(newPet);
                    petListAdapter.notifyDataSetChanged();

                    Toast.makeText(AddPetActivity.this, "Ljubimac dodan!", Toast.LENGTH_SHORT).show();

                    // Zatvaranje aktivnosti
                    finish();
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
            petImageView.setImageURI(imageUri);
        }
    }

    private Uri getImageUriFromPetImageView() {
        Drawable drawable = petImageView.getDrawable();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();

        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Image Description", null);
        return Uri.parse(path);
    }
}
