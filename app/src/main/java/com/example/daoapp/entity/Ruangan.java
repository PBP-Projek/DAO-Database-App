package com.example.daoapp.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Ruangan {
    @PrimaryKey(autoGenerate = true)
    private int idRuangan;
    private String namaRuangan;
    private int kapasitas;
    private int idGedung;

    //constructor
    public Ruangan(int idRuangan, String namaRuangan, int kapasitas, int idGedung) {
        this.idRuangan = idRuangan;
        this.namaRuangan = namaRuangan;
        this.kapasitas = kapasitas;
        this.idGedung = idGedung;
    }

    //getter and setter
    public int getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(int idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public int getIdGedung() {
        return idGedung;
    }

    public void setIdGedung(int idGedung) {
        this.idGedung = idGedung;
    }

}
