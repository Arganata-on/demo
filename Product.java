public class Product {
  private int kode;
  private String nama;
  private int harga;
  private int stock;

  public Product(int kode, String nama, int harga, int stock) {
    this.kode = kode;
    this.nama = nama;
    this.harga = harga;
    this.stock = stock;
  }

  public int getKode() {
    return kode;
  }

  public void setKode(int kode) {
    this.kode = kode;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public int getHarga() {
    return harga;
  }

  public void setHarga(int harga) {
    this.harga = harga;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
}