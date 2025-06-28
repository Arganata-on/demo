CREATE TABLE `products` (
  `kode` int NOT NULL,
  `nama` varchar(100) NOT NULL,
  `harga` int NOT NULL,
  `stock` int NOT NULL
) ;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`kode`, `nama`, `harga`, `stock`) VALUES
(1, 'Beras Premium 5kg', 75000, 20),
(2, 'Minyak Goreng 1L', 18000, 50),
(3, 'Gula Pasir 1kg', 14000, 40),
(4, 'Mie Instan Ayam', 3500, 100),
(5, 'Susu Kental Manis', 12000, 30),
(6, 'Kopi Bubuk 200g', 25000, 25),
(7, 'Sabun Mandi Cair 400ml', 17000, 35),
(8, 'Shampoo 200ml', 22000, 40),
(9, 'Tepung Terigu 1kg', 11000, 45),
(11, 'Detergen Bubuk 1kg', 21000, 40),
(12, 'Pasta Gigi 150g', 13000, 30),
(13, 'Sikat Gigi Medium', 6000, 50),
(14, 'Tisu Gulung 2 Roll', 9000, 45),
(16, 'Saos Tomat 500ml', 15000, 20),
(17, 'Biskuit Coklat 200g', 12000, 60),
(21, 'cayan', 2000, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`kode`);
COMMIT;