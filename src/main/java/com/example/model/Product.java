package com.example.model;

public class Product {
    private int id_produk;
    private String nama;
    private int harga;
    private int stok;

    public Product(int id_produk, String nama, int harga, int stok) {
        this.id_produk = id_produk;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
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

}