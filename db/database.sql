<<<<<<< HEAD
-- Active: 1751211854122@@127.0.0.1@3306@toko_db

DROP DATABASE toko_db;

=======
>>>>>>> 536ba003ebf1d8207420ebef1f398f09cc62cd70
CREATE DATABASE toko_db;

USE toko_db;

CREATE TABLE categories (
    id_kategori INT(11) PRIMARY KEY,
    nama_kategori VARCHAR(100)
);

CREATE TABLE products (
    id_produk INT(11) PRIMARY KEY,
    nama VARCHAR(100),
    harga INT(11),
    stok INT(11),
    id_kategori INT(11),
    FOREIGN KEY (id_kategori) REFERENCES categories (id_kategori) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE transactions (
    id_transaksi INT(11) PRIMARY KEY AUTO_INCREMENT,
    jumlah_dibeli INT(11),
    id_produk INT(11),
    FOREIGN KEY (id_produk) REFERENCES products (id_produk) ON DELETE CASCADE
);

<<<<<<< HEAD
UPDATE FROM categories SET nama_kategori ="Senjata Emakk" WHERE id_kategori =3 ;

INSERT INTO
    categories (id_kategori, nama_kategori)
VALUES 
    (1, 'Minuman'),
    (2, 'Makanan'),
    (3, 'Peralatan Rumah Tangga');

INSERT INTO
    products (id_produk, nama, harga, stok, id_kategori)
VALUES 
    (1, 'Teh Botol Sosro', 5000, 50, 1),
    (2, 'Aqua Botol 600ml', 4000, 60, 1),
    (3, 'Kopi Kapal Api', 15000, 40, 1),
    (4, 'Sprite Kaleng', 8000, 30, 1),
    (5, 'Fanta Botol', 7000, 25, 1),
    (6, 'Chitato', 8000, 40, 2),
    (7, 'Oreo', 6000, 50, 2),
    (8, 'Taro Snack', 7000, 35, 2),
    (9, 'SilverQueen', 15000, 20, 2),
    (10, 'Tango Wafer', 5000, 45, 2),
    (11, 'Sapu Lantai', 25000, 20, 3),
    (12, 'Ember Plastik', 18000, 30, 3),
    (13, 'Gayung', 7000, 50, 3),
    (14, 'Lap Pel', 20000, 25, 3),
    (15, 'Tempat Sampah', 30000, 15, 3),
    (16, 'Sabun Cuci Piring', 12000, 40, 3),
    (17, 'Pisau Dapur', 22000, 20, 3),
    (18, 'Sendok Stainless', 3000, 100, 3),
    (19, 'Gelas Plastik', 4000, 80, 3),
    (20, 'Wajan Anti Lengket', 55000, 10, 3);

=======
INSERT INTO
    categories (id_kategori, nama_kategori)
VALUES 
    (1, 'Minuman'),
    (2, 'Makanan'),
    (3, 'Peralatan Rumah Tangga');

INSERT INTO
    products (id_produk, nama, harga, stok, id_kategori)
VALUES 
    (1, 'Teh Botol Sosro', 5000, 50, 1),
    (2, 'Aqua Botol 600ml', 4000, 60, 1),
    (3, 'Kopi Kapal Api', 15000, 40, 1),
    (4, 'Sprite Kaleng', 8000, 30, 1),
    (5, 'Fanta Botol', 7000, 25, 1),
    (6, 'Chitato', 8000, 40, 2),
    (7, 'Oreo', 6000, 50, 2),
    (8, 'Taro Snack', 7000, 35, 2),
    (9, 'SilverQueen', 15000, 20, 2),
    (10, 'Tango Wafer', 5000, 45, 2),
    (11, 'Sapu Lantai', 25000, 20, 3),
    (12, 'Ember Plastik', 18000, 30, 3),
    (13, 'Gayung', 7000, 50, 3),
    (14, 'Lap Pel', 20000, 25, 3),
    (15, 'Tempat Sampah', 30000, 15, 3),
    (16, 'Sabun Cuci Piring', 12000, 40, 3),
    (17, 'Pisau Dapur', 22000, 20, 3),
    (18, 'Sendok Stainless', 3000, 100, 3),
    (19, 'Gelas Plastik', 4000, 80, 3),
    (20, 'Wajan Anti Lengket', 55000, 10, 3);

>>>>>>> 536ba003ebf1d8207420ebef1f398f09cc62cd70
DELIMITER $$

CREATE TRIGGER kurangi_stok_setelah_penjualan
AFTER INSERT ON transactions
FOR EACH ROW
BEGIN
    UPDATE products
    SET stok = stok - NEW.jumlah_dibeli
    WHERE id_produk = NEW.id_produk;
END$$

<<<<<<< HEAD
DELIMITER ;
=======
DELIMITER ;
>>>>>>> 536ba003ebf1d8207420ebef1f398f09cc62cd70
