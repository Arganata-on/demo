import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.*;
import javax.swing.border.EmptyBorder;

public class ProductFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton btnLoad, btnInsert, btnUpdate, btnDelete;

    public ProductFrame() {
        setTitle("Kasir Toko - UAS PBO");

        // Set custom icon
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("./src/logo.png")); // Correct path for resources
            if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                setIconImage(icon.getImage());
            } else {
                System.err.println("Warning: Icon not loaded. Check path or file.");
            }
        } catch (Exception e) {
            System.err.println("Error loading icon: " + e.getMessage());
        }

        setSize(900, 500); // Increased size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen

        // Model for the table
        model = new DefaultTableModel(new String[] { "Kode Produk", "Nama Produk", "Harga", "Stok" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        table = new JTable(model);

        // --- Table Styling ---
        table.setFillsViewportHeight(true); // Make the table fill the scroll pane
        table.setRowHeight(30); // Set row height
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14)); // Header font
        // table.getTableHeader().setBackground(new Color(70, 130, 180)); // Header
        // background color (SteelBlue)
        table.getTableHeader().setForeground(Color.WHITE); // Header text color

        // Center align "Kode" and "Harga" columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Kode

        // Custom renderer untuk kolom Harga (Rp)
        DefaultTableCellRenderer hargaRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value instanceof Integer) {
                    setText(String.format("Rp %,d", (Integer) value).replace(',', '.'));
                } else if (value instanceof Long) {
                    setText(String.format("Rp %,d", (Long) value).replace(',', '.'));
                }
                setHorizontalAlignment(JLabel.LEFT); // Rata kiri
                return c;
            }
        };

        table.getColumnModel().getColumn(2).setCellRenderer(hargaRenderer); // Harga
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Stock

        // Set preferred column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(80); // Kode
        table.getColumnModel().getColumn(1).setPreferredWidth(250); // Nama
        table.getColumnModel().getColumn(2).setPreferredWidth(120); // Harga
        table.getColumnModel().getColumn(3).setPreferredWidth(80); // Stock

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around table

        // --- Button Styling ---
        btnLoad = new JButton("Load Data");
        btnInsert = new JButton("Tambah Produk");
        btnUpdate = new JButton("Update Produk");
        btnDelete = new JButton("Hapus Produk");

        // Apply a consistent look to buttons
        JButton[] buttons = { btnLoad, btnInsert, btnUpdate, btnDelete };
        for (JButton btn : buttons) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
            // btn.setBackground(new Color(0, 123, 255)); // Bootstrap primary blue
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false); // Remove focus border
            btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 8, 15)); // Padding
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        }

        // --- Panel for Buttons ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15)); // Centered, with gaps
        // buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background
        // for the panel
        buttonPanel.setBorder(new EmptyBorder(5, 0, 5, 0)); // Padding

        buttonPanel.add(btnLoad);
        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        // --- Main Layout ---
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Add a header panel (optional, but improves appearance) ---
        JPanel headerPanel = new JPanel();
        // headerPanel.setBackground(new Color(52, 152, 219)); // Lighter blue for
        // header
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel titleLabel = new JLabel("Manajemen Data Produk");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        // titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Action Listeners
        btnLoad.addActionListener(e -> loadData());
        btnInsert.addActionListener(e -> insertData());
        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> deleteData());

        // Load data on startup
        loadData();
    }

    private void loadData() {
        model.setRowCount(0); // Clear existing data
        try (Connection conn = DBConnection.getKoneksi()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("kode"),
                        rs.getString("nama"),
                        rs.getInt("harga"),
                        rs.getInt("stock")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saat memuat data: " + e.getMessage(), "Kesalahan Database",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertData() {
        // Use a JPanel for better input field arrangement
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5)); // Rows, Cols, Hgap, Vgap
        JTextField kodeField = new JTextField(15);
        JTextField namaField = new JTextField(15);
        JTextField hargaField = new JTextField(15);
        JTextField stockField = new JTextField(15);

        inputPanel.add(new JLabel("Kode Produk:"));
        inputPanel.add(kodeField);
        inputPanel.add(new JLabel("Nama Produk:"));
        inputPanel.add(namaField);
        inputPanel.add(new JLabel("Harga:"));
        inputPanel.add(hargaField);
        inputPanel.add(new JLabel("Stok:"));
        inputPanel.add(stockField);

        int option = JOptionPane.showConfirmDialog(this, inputPanel, "Tambah Produk Baru", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            try {
                // Input validation
                int kode = Integer.parseInt(kodeField.getText());
                String nama = namaField.getText().trim();
                int harga = Integer.parseInt(hargaField.getText());
                int stock = Integer.parseInt(stockField.getText());

                if (nama.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nama produk tidak boleh kosong.", "Validasi Input",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try (Connection conn = DBConnection.getKoneksi()) {
                    PreparedStatement ps = conn
                            .prepareStatement("INSERT INTO products (kode, nama, harga, stock) VALUES (?, ?, ?, ?)");
                    ps.setInt(1, kode);
                    ps.setString(2, nama);
                    ps.setInt(3, harga);
                    ps.setInt(4, stock);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Produk berhasil ditambahkan!", "Sukses",
                            JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error saat menambahkan data: " + e.getMessage(),
                            "Kesalahan Database", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Pastikan Kode, Harga, dan Stok adalah angka yang valid.",
                        "Validasi Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diupdate terlebih dahulu.", "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int kode = (int) model.getValueAt(row, 0);
        String nama = (String) model.getValueAt(row, 1);
        int harga = (int) model.getValueAt(row, 2);
        int stock = (int) model.getValueAt(row, 3);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField namaField = new JTextField(nama, 15);
        JTextField hargaField = new JTextField(String.valueOf(harga), 15);
        JTextField stockField = new JTextField(String.valueOf(stock), 15);

        inputPanel.add(new JLabel("Nama Produk:"));
        inputPanel.add(namaField);
        inputPanel.add(new JLabel("Harga:"));
        inputPanel.add(hargaField);
        inputPanel.add(new JLabel("Stok:"));
        inputPanel.add(stockField);

        int option = JOptionPane.showConfirmDialog(this, inputPanel, "Update Produk (Kode: " + kode + ")",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String newNama = namaField.getText().trim();
                int newHarga = Integer.parseInt(hargaField.getText());
                int newStock = Integer.parseInt(stockField.getText());

                if (newNama.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nama produk tidak boleh kosong.", "Validasi Input",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try (Connection conn = DBConnection.getKoneksi()) {
                    PreparedStatement ps = conn
                            .prepareStatement("UPDATE products SET nama=?, harga=?, stock=? WHERE kode=?");
                    ps.setString(1, newNama);
                    ps.setInt(2, newHarga);
                    ps.setInt(3, newStock);
                    ps.setInt(4, kode);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Produk berhasil diupdate!", "Sukses",
                            JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error saat mengupdate data: " + e.getMessage(),
                            "Kesalahan Database", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Pastikan Harga dan Stok adalah angka yang valid.",
                        "Validasi Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus terlebih dahulu.", "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int kode = (int) model.getValueAt(row, 0);
        String nama = (String) model.getValueAt(row, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus produk '" + nama + "' (Kode: " + kode + ")?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DBConnection.getKoneksi()) {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE kode=?");
                ps.setInt(1, kode);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Produk berhasil dihapus!", "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                loadData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error saat menghapus data: " + e.getMessage(),
                        "Kesalahan Database", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // You would typically have a main method in another class to run this.
    // For testing purposes, you can add one here:
    public static void main(String[] args) {
        // Ensure that the database connection is available and the 'products' table
        // exists.
        // For example, if DBConnection is in the same package:
        SwingUtilities.invokeLater(() -> {
            new ProductFrame().setVisible(true);
        });
    }
}