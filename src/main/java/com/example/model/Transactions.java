package com.example.model;

public class Transactions {
    private int id_transaksi;
    private String nama;
    private String nama_kategori;
    private int harga;
    private int jumlah_dibeli;

    public Transactions(int id_transaksi, String nama, String nama_kategori, int harga, int jumlah_dibeli) {
        this.id_transaksi = id_transaksi;
        this.nama = nama;
        this.nama_kategori = nama_kategori;
        this.harga = harga;
        this.jumlah_dibeli = jumlah_dibeli;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah_dibeli() {
        return jumlah_dibeli;
    }

    public void setJumlah_dibeli(int jumlah_dibeli) {
        this.jumlah_dibeli = jumlah_dibeli;
    }
}
