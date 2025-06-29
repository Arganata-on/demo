package com.example.model;

public class Kategori {
    private int idKategori;
    private String kategoriNama;

    public Kategori(int idKategori, String KategoriNama) {
        this.idKategori = idKategori;
        this.kategoriNama = KategoriNama;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public String getKategoriNama() {
        return kategoriNama;
    }

    public void setKategoriNama(String KategoriNama) {
        this.kategoriNama = KategoriNama;
    }

    @Override
    public String toString() {
        return this.kategoriNama;
    }

}
