-- Active: 1748068829425@@127.0.0.1@3306@toko_db
CREATE DATABASE toko_db;

USE toko_db;

CREATE TABLE categories
(
    id_kategori   INT(11) PRIMARY KEY,
    nama_kategori VARCHAR(100)
);

CREATE TABLE products
(
    id_produk   INT(11) PRIMARY KEY,
    nama        VARCHAR(100),
    harga       INT(11),
    stok        INT(11),
    id_kategori INT(11),
    FOREIGN KEY (id_kategori) REFERENCES categories (id_kategori) ON DELETE CASCADE
);

CREATE TABLE transactions
(
    id_transaksi  INT(11),
    id_produk     INT(11),
    jumlah_dibeli INT(11),
    FOREIGN KEY (id_produk) REFERENCES products (id_produk)
);

INSERT INTO categories
values (1, 'Elektronik'),
       (2, 'Pakaian');

insert into products
values (1, 'Handphone', 2500000, 5, 1),
       (2, 'Sepatu', 120000, 12, 2),
       (3, 'Sandal', 13500, 29, 2);

insert into transactions
values (1, 3, 2),
       (2, 1, 1),
       (3, 3, 4);

DELIMITER $$

CREATE TRIGGER kurangi_stok_setelah_penjualan
    AFTER INSERT
    ON transactions
    FOR EACH ROW
BEGIN
    UPDATE products
    SET stok = stok - NEW.jumlah_dibeli
    WHERE id_produk = NEW.id_produk;
END$$

DELIMITER ;

SELECT *
FROM products;