import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Saiful Nur Budiman
 *         Teknik Informatika - Univ.Islam Balitar
 *         2024-2025
 */

public class DBConnection {
    static final String DB_URL = "jdbc:mysql://localhost:3307/pirmandb";
    static final String DB_USER = "root";
    static final String DB_PASS = "";

    public static void main(String[] args) {
        Connection connection = getKoneksi();
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Koneksi ditutup");
            } catch (SQLException e) {
                System.err.println("Error saat menutup koneksi " + e.getMessage());
            }
        }
    }

    public static Connection getKoneksi() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // penting untuk Java versi lama
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            if (conn != null) {
                System.out.println("Berhasil mak koneksinya");
            } else {
                System.out.println("Koneksi gagal le");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error koneksi = " + e.getMessage());
        }
        return conn;
    }

}