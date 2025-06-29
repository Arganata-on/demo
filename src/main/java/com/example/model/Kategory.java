package com.example.model;

public class Kategory {
    private int idKategory;
    private String kategoryNama;

    public Kategory(int idKategory, String kategoryNama) {
        this.idKategory = idKategory;
        this.kategoryNama = kategoryNama;
    }

    public int getIdKategory() {
        return idKategory;
    }

    public void setIdKategory(int idKategory) {
        this.idKategory = idKategory;
    }

    public String getKategoryNama() {
        return kategoryNama;
    }

    public void setKategoryNama(String kategoryNama) {
        this.kategoryNama = kategoryNama;
    }

    @Override
    public String toString() {
        return this.kategoryNama;
    }

}
