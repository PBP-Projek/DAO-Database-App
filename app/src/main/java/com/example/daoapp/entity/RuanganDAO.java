package com.example.daoapp.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RuanganDAO {
    @Query("SELECT * FROM ruangan")
    List<Ruangan> getAllRuangan();

    @Query("SELECT * FROM ruangan WHERE idGedung = :idGedung")
    List<Ruangan> getAllRuanganByIdGedung(int idGedung);

    @Insert
    void insertRuangan(Ruangan ruangan);

    @Delete
    void deleteRuangan(Ruangan ruangan);

    @Update
    void updateRuangan(Ruangan ruangan);
}
