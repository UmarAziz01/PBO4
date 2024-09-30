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
