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

INSERT INTO categories (id_categories, nama_kategori)
values (1, 'FPS'),
       (2, 'RPG')
       (3, 'HORROR')
       (4, 'RACING')
       (5, 'STRATEGY');

INSERT INTO products (id_produk, nama, harga, stok, id_kategori)
VALUES
(10101, 'Call of Duty: Modern Warfare II', 699000, 45, 1),   -- FPS
(10102, 'Battlefield 2', 400000, 150, 1),                    -- FPS
(10103, 'DOOM Eternal', 599000, 20, 1),                      -- FPS
(10104, 'Far Cry 6', 749000, 18, 1),                         -- FPS

(10105, 'The Elder Scrolls V: Skyrim', 399000, 30, 2),       -- RPG
(10106, 'Cyberpunk 2077', 899000, 25, 2),                    -- RPG
(10107, 'Final Fantasy XV', 499000, 22, 2),                  -- RPG
(10108, 'Persona 5 Royal', 649000, 28, 2),                   -- RPG

(10109, 'Amnesia: The Bunker', 289000, 10, 3),               -- HORROR
(10110, 'The Medium', 349000, 15, 3),                        -- HORROR
(10111, 'Phasmophobia', 159000, 50, 3),                      -- HORROR
(10112, 'Dead Space Remake', 799000, 17, 3),                 -- HORROR

(10113, 'Gran Turismo 7', 899000, 19, 4),                    -- RACING
(10114, 'F1 2023', 849000, 14, 4),                           -- RACING
(10115, 'WRC Generations', 499000, 26, 4),                   -- RACING
(10116, 'TrackMania Turbo', 299000, 40, 4),                  -- RACING

(10117, 'StarCraft II', 240000, 80, 5),                      -- STRATEGY
(10118, 'XCOM 2', 449000, 35, 5),                            -- STRATEGY
(10119, 'Total War: Warhammer III', 899000, 20, 5),          -- STRATEGY
(10120, 'Company of Heroes 3', 799000, 22, 5);               -- STRATEGY


insert into transactions (id_transaksi, id_produk, jumlah_dibeli)
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