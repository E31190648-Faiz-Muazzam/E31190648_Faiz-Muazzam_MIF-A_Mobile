package com.faizmuazzam.kelompok6_manajemenfile;
//Import class yang akan di gunakan dalam Halaman Main2Activity.java
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

//Membuat class utama di mana di dalam class merupakan class yang akan di jalan kan dalam halaman MainActivity
public class Main2Activity extends AppCompatActivity {
    //   Membuat Deklarasi Variabel yang akan di gunakan dalam pembuatan aplikasi
    TextView showText;
    //    Membuat fungsi di mana di dalamnya terdapat pemanggilan file xml yang akan di jalan kan dalam program
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        Memanggil file activity_main.xml yang merupakan bentuk desain aplikasi
        setContentView(R.layout.activity_main2);
//        menangkap texview dengan id getText yang diman akan di gunakan untuk menampilkan nama file yang tersimpan
        showText = (TextView) findViewById(R.id.getText);
    }

    //    Pembuatan fungsi next yang bertujuan untuk membuat fungsi pindah halaman (intent)  yang nantinya akan
//    di panggil dalam tombol Back
    public void back(View view){
        //        Membuat deklarasi intent yang dimana berfungsi untuk memindahkan layar ke Main Activity
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
    }
//    Membuat fungsi getPublic yang di gunakan untuk menangkap file pubic yang sudah di buat
    public void  getPublic(View view){
        //        mengecheck penyimpanan directory di penyimpanan internal
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //        Check file myData1.txt
        File myFile = new File(folder,"myData1.txt");
//        menagngkap data yang tersimpan di dalamnya
        String text = getdata(myFile);
//        membuat perbandingan untuk menampilakan data
        if (text != null){
//            tampilan textview jika benar
            showText.setText(text);
        } else {
//            Tampilan textview jika salah
            showText.setText("NO DATA");
        }
    }

    public void getPrivate(View view){
//        Check direktory kelompok6
        File folder = getExternalFilesDir("kelompok6");
//        check file mydata2.txt yang ada di directory
        File myFile = new File(folder,"mydata2.txt");
//         menagngkap data yang tersimpan di dalamnya
        String text = getdata(myFile);
        //membuat perbandingan untuk menampilakan data
        if (text != null){
            //tampilan textview jika benar
            showText.setText(text);
        } else {
            //tampilan textview jika salah
            showText.setText("NO DATA");
        }
    }

    private String getdata(File myFile){
        //       Deklarasi variabel yang di gunakan untuk menangkap data di penyimpanan
        FileInputStream fileInputStream = null;
        try {
            //menangkap file
            fileInputStream = new FileInputStream(myFile);
            int i = -1;
            StringBuffer buffer = new StringBuffer();
            while ((i = fileInputStream.read())!=-1){
                buffer.append((char)i);
            }
//            deklarasi data yang ditangkap menjadi String
            return buffer.toString();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (fileInputStream !=null){
                try {
                    fileInputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}