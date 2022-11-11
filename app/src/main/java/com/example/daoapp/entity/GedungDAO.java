package com.example.daoapp.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GedungDAO {
    @Query("SELECT * FROM gedung")
    List<Gedung> getAllGedung();

    @Insert
    void insertGedung(Gedung gedung);

    @Delete
    void deleteGedung(Gedung gedung);

    @Update
    void updateGedung(Gedung gedung);

}
