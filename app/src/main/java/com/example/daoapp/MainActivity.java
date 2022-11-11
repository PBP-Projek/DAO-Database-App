package com.example.daoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.daoapp.adapter.GedungAdapter;
import com.example.daoapp.adapter.RuanganAdapter;
import com.example.daoapp.entity.AppDatabase;
import com.example.daoapp.entity.Gedung;
import com.example.daoapp.entity.Ruangan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvGedung;
    private FloatingActionButton fabAdd;
    private AppDatabase appDatabase;
    private GedungAdapter gedungAdapter;
    private List<Gedung> gedungs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Gedung");

        rvGedung = findViewById(R.id.rvGedung);
        fabAdd = findViewById(R.id.fabAdd);


        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "fsm").build();

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TambahGedung.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        getAllGedung();
    }

    private void setRvGedung(List<Gedung> gedungs) {
        gedungAdapter = new GedungAdapter(gedungs, (position,view) -> {
            PopupMenu popupMenu = new PopupMenu(this, view);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.edit:
//                        Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
                        editGedung(gedungs.get(position).getIdGedung());
                        break;
                    case R.id.delete:
//                        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                        deleteGedung(position);
                        break;
                }
                return false;
            });
            popupMenu.show();
        }, (position,view) -> {
            Intent intent = new Intent(MainActivity.this, ListRuangan.class);
            intent.putExtra("idGedung", gedungs.get(position).getIdGedung());
            startActivity(intent);
        });
        rvGedung.setAdapter(gedungAdapter);
        rvGedung.setLayoutManager(new GridLayoutManager(this,2));
    }

    private void editGedung(int idGedung) {
        Intent intent = new Intent(MainActivity.this, EditGedung.class);
        intent.putExtra("idGedung", idGedung);
        startActivity(intent);
    }

    private void deleteGedung(int id) {
        AsyncTask.execute(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                appDatabase.gedungDao().deleteGedung(gedungs.get(id));
                gedungs.remove(gedungs.get(id));
            }

            runOnUiThread(() -> {
                setRvGedung(gedungs);

            });
        });
    }

    public void getAllGedung() {
        gedungs = new ArrayList<>();

        AsyncTask.execute(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                gedungs.addAll(appDatabase.gedungDao().getAllGedung());
            }

            runOnUiThread(() -> {
                setRvGedung(gedungs);
                Log.d("12345678", "getAllGedung: "+gedungs.size());
            });
        });
    }

}