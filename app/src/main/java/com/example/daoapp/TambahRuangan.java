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
import com.example.daoapp.entity.Ruangan;

public class TambahRuangan extends AppCompatActivity {
    private EditText etNamaRuangan, etKapasitas;
    private Button btnSubmitRuangan;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_ruangan);

        etNamaRuangan = findViewById(R.id.etNamaRuangan);
        etKapasitas = findViewById(R.id.etKapasitas);
        btnSubmitRuangan = findViewById(R.id.btnSubmitRuangan);

        btnSubmitRuangan.setOnClickListener(v -> {
            String namaRuangan = etNamaRuangan.getText().toString();
            int kapasitas = Integer.parseInt(etKapasitas.getText().toString());

            addRuangan(namaRuangan, kapasitas);
        });
    }

    private void addRuangan(String namaRuangan, int kapasitas) {
        AsyncTask.execute(() -> {
            appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "fsm").build();
            appDatabase.ruanganDao().insertRuangan(new Ruangan(0, namaRuangan, kapasitas));
            runOnUiThread(() -> {
                Toast.makeText(getApplicationContext(), "Ruangan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}