package com.example.daoapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Gedung {
    @PrimaryKey(autoGenerate = true)
    private int idGedung;
    private String namaGedung;
    private String prodi;

    //constructor
    public Gedung(int idGedung, String namaGedung, String prodi) {
        this.idGedung = idGedung;
        this.namaGedung = namaGedung;
        this.prodi = prodi;
    }

    //getter and setter
    public int getIdGedung() {
        return idGedung;
    }

    public void setIdGedung(int idGedung) {
        this.idGedung = idGedung;
    }

    public String getNamaGedung() {
        return namaGedung;
    }

    public void setNamaGedung(String namaGedung) {
        this.namaGedung = namaGedung;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }
}
