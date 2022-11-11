package com.example.daoapp.entity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Gedung.class, Ruangan.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GedungDAO gedungDao();
    public abstract RuanganDAO ruanganDao();
}
