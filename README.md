# Java CRUD x PostgreSQL😊🤗👋

Saya Umar Abdul Aziz akan mencoba untuk mengimplementasikan Java CRUD berbasis text yang akan mengintegrasikan dengan Postgree SQL menggunakan library JDBC dengan beberapa penangganan error yakni try-catch, throw, dan custom exception 

### **Menu yang tersedia**

* **Memasukkan data** 
* **Menampilkan data**
* **Menghapus data** 
* **Memperbarui/mengubah data**
* **Menghapus semua data dari tabel**
* **Mencari data sesuai dengan NIM yang diinginkan**

## Kontak
https://www.linkedin.com/in/umar-abdul-aziz-b95435273/
https://www.instagram.com/u_maraziz/

'#'Penugasan matakuliah pemrograman berorientasi obyek keempat

------------------------------------------------------------------------------------------
### Menerapkan Java CRUD, Exception (Try-Catch, Throws, dan Custom)


# Langkah Pertama  
**Membuat Custom Exception** kelas ini digunakan untuk membuat sebuah exception khusus yang bisa dilempar (throw) ketika data yang dicari tidak ditemukan
```java
public class DataTidakDitemukanException extends Exception {

    public DataTidakDitemukanException(String message) {
        super(message);
    }
}
```

# Langkah Kedua  
**Membuat CRUD Program** yang didalamnya terdapat main class untuk program CRUD, deklarasi variabel dan pembuatan 4 method utama -CRUD- + 2 method tambahan yakni hapusSemua() dan cariDataByNIM(). Method hapusSemua digunakan untuk menghapus semua baris yang telah terekam sebelumnya di database, sedangkan method cariDataByNIM() digunakan untuk mencari satu baris dengan NIM yang diinginkan
```
Ikuti kode program yang sudah saya bagikan diatas
```

# Langkah Ketiga  
**Mengimplementasikan Exception** pada program ini terdapat beberapa penanganan error dibeberapa method namun saya akan menjelaskan pada bagian cariDataByNIM(). method tersebut akan menjalankan beberapa langkah untuk memenuhi kebutuhan program yakni mulai dari berkoneksi dengan database, meminta input an nim yang diinginkan pengguna, menjalankan query untuk mencari data berdasarkan NIM, jika data ditemukan maka akan menampilkan hasil pencarian dari database, jika tidak data yang ditemukan maka custom exception menanggani program dan menampilkan pesan error kepada pengguna.
```
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

Dan ikuti kode program yang sudah saya bagikan diatas
```
