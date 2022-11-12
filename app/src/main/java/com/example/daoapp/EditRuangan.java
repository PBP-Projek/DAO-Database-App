package com.example.daoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daoapp.entity.AppDatabase;
import com.example.daoapp.entity.Ruangan;

public class EditRuangan extends AppCompatActivity {
    private int idRuangan, idGedung;
    private EditText etNamaRuangan, etKapasitas;
    private Button btnUpdateRuangan;
    private AppDatabase appDatabase;
    String namaRuangan;
    int kapasitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ruangan);

        idRuangan = getIntent().getIntExtra("idRuangan", 0);
        idGedung = getIntent().getIntExtra("idGedung", 0);
        etNamaRuangan = findViewById(R.id.etNamaRuangan);
        etKapasitas = findViewById(R.id.etKapasitas);
        btnUpdateRuangan = findViewById(R.id.btnUpdateRuangan);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "fsm").build();

        namaRuangan = getIntent().getStringExtra("namaRuangan");
        kapasitas = getIntent().getIntExtra("kapasitas", 0);

        btnUpdateRuangan.setOnClickListener(v -> {
            updateRuangan();
        });
    }

    private void updateRuangan() {
        namaRuangan = etNamaRuangan.getText().toString();
        kapasitas = Integer.parseInt(etKapasitas.getText().toString());

        AsyncTask.execute(() -> {
            appDatabase.ruanganDao().updateRuangan(new Ruangan(idRuangan, namaRuangan, kapasitas, idGedung));
            runOnUiThread(() -> {
                Toast.makeText(getApplicationContext(), "Ruangan berhasil diupdate", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}