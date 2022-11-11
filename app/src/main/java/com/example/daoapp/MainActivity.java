package com.example.daoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.daoapp.entity.AppDatabase;
import com.example.daoapp.entity.Gedung;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvGedung;
    private FloatingActionButton fabAdd;
    private AppDatabase appDatabase;
    private GedungAdapter gedungAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvGedung = findViewById(R.id.rvGedung);
        fabAdd = findViewById(R.id.fabAdd);


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "fsm").build();
                Log.d("TAG","INSERTING");
                appDatabase.gedungDao().insertGedung(new Gedung(1,"Gedung A","Informatika"));
                int recordSize = appDatabase.gedungDao().getAllGedung().size();
                Log.d("TAG","DISPLAY RECORD SIZE: "+recordSize);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        getAllGedung();
    }

    private void setRvGedung(List<Gedung> gedungs) {
        gedungAdapter = new GedungAdapter(gedungs);
        rvGedung.setAdapter(gedungAdapter);
        rvGedung.setLayoutManager(new LinearLayoutManager(this));
    }

    public void getAllGedung() {
        List<Gedung> gedungs = new ArrayList<>();

        AsyncTask.execute(() -> {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                gedungs.addAll(appDatabase.gedungDao().getAllGedung());
//            }

            runOnUiThread(() -> {
                setRvGedung(gedungs);
            });

            AsyncTask.Status status = AsyncTask.Status.FINISHED;
        });
    }
}