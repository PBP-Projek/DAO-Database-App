package com.example.daoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.daoapp.adapter.RuanganAdapter;
import com.example.daoapp.entity.AppDatabase;
import com.example.daoapp.entity.Ruangan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListRuangan extends AppCompatActivity {
    private RecyclerView rvRuangan;
    private FloatingActionButton fab;
    private AppDatabase appDatabase;
    private RuanganAdapter ruanganAdapter;
    private List<Ruangan> ruangans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ruangan);
        getSupportActionBar().setTitle("Ruangan");

        rvRuangan = findViewById(R.id.rvRuangan);
        fab = findViewById(R.id.fab);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "fsm").build();

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(ListRuangan.this, TambahRuangan.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getAllRuangan();
    }

    private void getAllRuangan() {
        ruangans = new ArrayList<>();

        AsyncTask.execute(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ruangans.addAll(appDatabase.ruanganDao().getAllRuangan());
            }

            runOnUiThread(() -> {
                setRvRuangan(ruangans);
                Log.d("12345678", "getAllRuangan: "+ruangans.size());
            });
        });
    }

    private void setRvRuangan(List<Ruangan> ruangans) {
        RuanganAdapter ruanganAdapter = new RuanganAdapter(ruangans);
        rvRuangan.setAdapter(ruanganAdapter);
        rvRuangan.setLayoutManager(new LinearLayoutManager(this));
    }
}