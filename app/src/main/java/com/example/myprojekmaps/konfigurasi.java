package com.example.myprojekmaps;
/**
 * Created by muhammadyusuf on 01/19/2017.
 * kodingindonesia
 */

public class konfigurasi {

    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://192.168.1.4/Android/masjid/tambahMasjid.php";
    public static final String URL_GET_ALL = "http://192.168.1.4/Android/pegawai/tampilSemuaMasjid.php";
    public static final String URL_GET_EMP = "http://192.168.1.4/Android/pegawai/tampilMasjid.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.1.4/Android/pegawai/updateMasjid.php";
    public static final String URL_DELETE_EMP = "http://192.168.1.4/Android/pegawai/hapusMasjid.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA_MASJID = "name";
    public static final String KEY_EMP_LONGITUDE = "long"; //long itu variabel untuk longitude
    public static final String KEY_EMP_LATITUDE = "lati"; //lati itu variabel untuk latitude

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA_MASJID = "name";
    public static final String TAG_LONGITUDE = "long";
    public static final String TAG_LATITUDE = "lati";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";
}