/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tugaspertemuankeempatfinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
//custom exception janlup
public class CRUDKu {

    Connection cn;
    Statement st;
    PreparedStatement ps;//st yang disiapkan
    static int sesi = 0;
    public static final String ANSI_Biru = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    String driver = "org.postgresql.Driver";
    String koneksi = "jdbc:postgresql://localhost:5432/PBO_P4_T4_A";
    String user = "postgres";
    String password = " ";  // Jangan lupa isi password PostgreSQL
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader input = new BufferedReader(inputStreamReader);

    public void tambah() {
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(koneksi, user, password);

            String sql = "INSERT INTO Mahasiswa (NIM, NamaMahasiswa, KelaminMahasiswa, UmurMahasiswa) VALUES (?, ?, ?, ?)";
            ps = cn.prepareStatement(sql);
            System.out.println("> Anda akan memasukkan data ke database anda.");
            System.out.print("NIM : ");
            String NIM = input.readLine().trim();
            System.out.print("Nama Mahasiswa : ");
            String NamaMahasiswa = input.readLine().trim();
            System.out.print("Kelamin (L/P) : ");
            String KelaminMahasiswa = input.readLine().trim();
            System.out.print("Umur : ");
            String UmurMahasiswa = input.readLine().trim();

            ps.setLong(1, Long.parseLong(NIM));
            ps.setString(2, NamaMahasiswa);
            ps.setString(3, KelaminMahasiswa);
            ps.setInt(4, Integer.parseInt(UmurMahasiswa));

            ps.executeUpdate();
            System.out.println("> Anda berhasil menginputkan data.");
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            System.out.println("Note : Pastikan untuk melakukan input dengan benar yakni L/P atau umur lebih dari atau sama dengan 16 tahun.");
            Logger.getLogger(CRUDKu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CRUDKu.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    public void tampil() {
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(koneksi, user, password);

            String sql = "SELECT * FROM Mahasiswa";
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println("> Data akan ditampilkan dari database anda.");
            System.out.println(" NIM | Nama | Kelamin | Umur ");
            while (rs.next()) {

                System.out.println(
                        rs.getLong("NIM") + "  | "
                        + rs.getString("NamaMahasiswa") + "  | "
                        + rs.getString("KelaminMahasiswa") + "  | "
                        + rs.getInt("UmurMahasiswa")
                );
            }
            System.out.println("> Data berhasil ditampilkan dari database anda.");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CRUDKu.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CRUDKu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void hapus() {
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(koneksi, user, password);

            String sql = "DELETE FROM Mahasiswa WHERE NIM = ?";
            ps = cn.prepareStatement(sql);

            System.out.print("Masukkan NIM yang akan dihapus: ");
            String NIM = input.readLine().trim();
            ps.setLong(1, Long.parseLong(NIM));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("> Data berhasil dihapus.");
            } else {
                System.out.println("> Data tidak ditemukan.");
            }
            System.out.println(" ");
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(CRUDKu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CRUDKu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update() {
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(koneksi, user, password);

            String sql = "UPDATE Mahasiswa SET NamaMahasiswa = ?, KelaminMahasiswa = ?, UmurMahasiswa = ? WHERE NIM = ?";
            ps = cn.prepareStatement(sql);

            System.out.print("Masukkan NIM yang akan diupdate: ");
            String NIM = input.readLine().trim();
            System.out.print("Nama Mahasiswa baru: ");
            String NamaMahasiswa = input.readLine().trim();
            System.out.print("Kelamin baru: ");
            String KelaminMahasiswa = input.readLine().trim();
            System.out.print("Umur baru: ");
            String UmurMahasiswa = input.readLine().trim();

            ps.setString(1, NamaMahasiswa);
            ps.setString(2, KelaminMahasiswa);
            ps.setInt(3, Integer.parseInt(UmurMahasiswa));
            ps.setLong(4, Long.parseLong(NIM));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("> Data berhasil diupdate.");
            } else {
                System.out.println("> Data belum ditemukan, silahkan coba sekali lagi.");
            }
            System.out.println(" ");
        } catch (ClassNotFoundException | SQLException | IOException ex) {
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CRUDKu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void hapusSemua() {
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(koneksi, user, password);

            // Mengonfirmasi apakah pengguna yakin untuk menghapus semua data
            System.out.print("> Apakah anda benar ingin menghapus semua data dari tabel mahasiswa? (B/S): ");
            String konfirmasi = input.readLine().trim();

            if (konfirmasi.equalsIgnoreCase("B")) {
                String sql = "DELETE FROM Mahasiswa";
                st = cn.createStatement();
                int rowsAffected = st.executeUpdate(sql);

                if (rowsAffected > 0) {
                    System.out.println("> Semua data berhasil dihapus.");
                } else {
                    System.out.println("> Tidak ada data yang ditemukan untuk dihapus.");
                }
            } else {
                System.out.println("> Penghapusan semua data dibatalkan.");
            }
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(CRUDKu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CRUDKu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//BAGIAN CUSTOM EXCEPTION
    public void cariDataByNIM() throws DataTidakDitemukanException {
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(koneksi, user, password);

            System.out.print("Masukkan NIM yang ingin dicari: ");
            String NIM = input.readLine().trim();

            String sql = "SELECT * FROM Mahasiswa WHERE NIM = ?";
            ps = cn.prepareStatement(sql);
            ps.setLong(1, Long.parseLong(NIM));
            ResultSet rs = ps.executeQuery();
            System.out.println("> Data akan ditampilkan dari database anda sesuai permintaan anda");
            System.out.println(" NIM | Nama | Kelamin | Umur ");
            if (rs.next()) {
                System.out.println(
                        rs.getLong("NIM") + "  | "
                        + rs.getString("NamaMahasiswa") + "  | "
                        + rs.getString("KelaminMahasiswa") + "  | "
                        + rs.getInt("UmurMahasiswa")
                );
            } else {
                throw new DataTidakDitemukanException("Data dengan NIM " + NIM + " tidak ditemukan.");
            }
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(CRUDKu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CRUDKu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//BAGIAN TRY-CATCH
    public void menu() {
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Masukkan data");
        System.out.println("2. Tampilkan data");
        System.out.println("3. Hapus data");
        System.out.println("4. Perbarui data");
        System.out.println("5. Hapus semua data dari tabel");
        System.out.println("6. Cari data sesuai NIM");
        System.out.println("0. Keluar");
        System.out.print("Pilihan mu >>> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());
            switch (pilihan) {
                case 0:
                    System.out.println("> Terima Kasih & Sampai Jumpa");
                    System.exit(0);
                    break;
                case 1:
                    tambah();
                    break;
                case 2:
                    tampil();
                    break;
                case 3:
                    hapus();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    hapusSemua();
                    break;
                case 6:
                    try {
                        cariDataByNIM();
                    } catch (DataTidakDitemukanException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Pilihan salah!");
            }
        } catch (IOException ex) {
            Logger.getLogger(CRUDKu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//BAGIAN THROWS
    public static void main(String[] args) throws Exception {

        while (true) {  // Menambahkan loop agar menu terus berjalan
            sesi++; // Menambah sesi setiap kali main dipanggil
            System.out.println(ANSI_Biru + "\nSesi ke-" + sesi + ANSI_RESET);
            new CRUDKu().menu();  // Memanggil menu di setiap sesi
            System.out.println("\n------------------------------------------");
        }

    }
}




