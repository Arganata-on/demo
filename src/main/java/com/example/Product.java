package com.example;

public class Product {
    private int id_produk;
    private String nama;
    private int harga;
    private int stok;
    private String nama_kategori;

    public Product(int id_produk, String nama, int harga, int stok, String nama_kategori) {
        this.id_produk = id_produk;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.nama_kategori = nama_kategori;
    }

    public int getId_produk() {
        return id_produk;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

}