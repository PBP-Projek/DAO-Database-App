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

public class TambahGedung extends AppCompatActivity {
    private EditText etNamaGedung, etProdi;
    private Button btnSubmitGedung;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_gedung);

        etNamaGedung = findViewById(R.id.etNamaGedung);
        etProdi = findViewById(R.id.etProdi);
        btnSubmitGedung = findViewById(R.id.btnSubmitGedung);

        btnSubmitGedung.setOnClickListener(v -> {
            String namaGedung = etNamaGedung.getText().toString();
            String prodi = etProdi.getText().toString();

            addGedung(namaGedung, prodi);
        });
    }

    private void addGedung(String namaGedung, String prodi) {
        AsyncTask.execute(() -> {
            appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "fsm").build();
            appDatabase.gedungDao().insertGedung(new Gedung(0, namaGedung, prodi));
            runOnUiThread(() -> {
                Toast.makeText(getApplicationContext(), "Gedung berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}