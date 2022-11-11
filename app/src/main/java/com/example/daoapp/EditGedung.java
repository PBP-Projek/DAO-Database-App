package com.example.daoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daoapp.entity.AppDatabase;
import com.example.daoapp.entity.Gedung;

public class EditGedung extends AppCompatActivity {
    private int idGedung;
    private EditText etNamaGedung, etProdi;
    private Button btnUpdateGedung;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gedung);

        idGedung = getIntent().getIntExtra("idGedung", 0);
        etNamaGedung = findViewById(R.id.etNamaGedung);
        etProdi = findViewById(R.id.etProdi);
        btnUpdateGedung = findViewById(R.id.btnUpdateGedung);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "fsm").build();

        btnUpdateGedung.setOnClickListener(v -> {
            String namaGedung = etNamaGedung.getText().toString();
            String prodi = etProdi.getText().toString();

            updateGedung(namaGedung, prodi);
        });

    }

    private void updateGedung(String namaGedung, String prodi) {
        AsyncTask.execute(() -> {
            appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "fsm").build();
            appDatabase.gedungDao().updateGedung(new Gedung(idGedung, namaGedung, prodi));
            runOnUiThread(() -> {
                Toast.makeText(getApplicationContext(), "Gedung berhasil diupdate", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}