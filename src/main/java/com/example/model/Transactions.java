package com.example.model;

public class Transactions {
    // Fields now use standard Java camelCase naming
    private int idTransaksi;
    private String nama;
    private String namaKategori;
    private int harga; // Represents the historical price per unit
    private int jumlahDibeli;
    private int totalHarga;

    public Transactions(int idTransaksi, String nama, String namaKategori, int harga, int jumlahDibeli, int totalHarga) {
        this.idTransaksi = idTransaksi;
        this.nama = nama;
        this.namaKategori = namaKategori;
        this.harga = harga;
        this.jumlahDibeli = jumlahDibeli;
        this.totalHarga = totalHarga;
    }

    // Getters and Setters also follow the camelCase convention
    public int getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(int idTransaksi) { this.idTransaksi = idTransaksi; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getNamaKategori() { return namaKategori; }
    public void setNamaKategori(String namaKategori) { this.namaKategori = namaKategori; }

    public int getHarga() { return harga; }
    public void setHarga(int harga) { this.harga = harga; }

    public int getJumlahDibeli() { return jumlahDibeli; }
    public void setJumlahDibeli(int jumlahDibeli) { this.jumlahDibeli = jumlahDibeli; }
    
    public int getTotalHarga() { return totalHarga; }
    public void setTotalHarga(int totalHarga) { this.totalHarga = totalHarga; }
}