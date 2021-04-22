package com.faizmuazzam.kelompok6_manajemenfile;

//Import class yang akan di gunakan dalam Halaman MainActivity.java
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//Membuat class utama di mana di dalam class merupakan class yang akan di jalan kan dalam halaman MainActivity
public class MainActivity extends AppCompatActivity {
//   Membuat Deklarasi Variabel yang akan di gunakan dalam pembuatan aplikasi
    EditText editText;
    String filename;
    String fileContent;
    private int STORAGE_PERMISSION_CODE = 23;

//    Membuat fungsi di mana di dalamnya terdapat pemanggilan file xml yang akan di jalan kan dalam program
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Memanggil file activity_main.xml yang merupakan bentuk desain aplikasi
        setContentView(R.layout.activity_main);
//        Menangkap apa yang di inputkan dalam editText tampilan
        editText = (EditText) findViewById(R.id.editText2);
//        Deklarasi nilai variabel filename
        filename = "myFile.txt";
//        Deklarasi nilai variabel filecontent
        fileContent = "myFileDir";


    }

//    Pembuatan fungsi next yang bertujuan untuk membuat fungsi pindah halaman (intent)  yang nantinya akan
//    di panggil dalam tombol click to view
    public void next(View view){
//        Membuat deklarasi intent yang dimana berfungsi untuk memindahkan layar ken Main2Activity
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }
//  Membuat fungsi savePublic untuk membuat fungsi menyimpan data Public
    public void savePublic(View view) {
//        Izin akses membaca external storage
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
//        mengubah type data yang di inputkan menjadi string
        String info = editText.getText().toString();
//        mengecheck penyimpanan directory di penyimpanan internal
        File folder = Environment.getExternalStorageDirectory();
//        membuat directory baru
        File createDir = new File(folder.getAbsolutePath()+"/Kelompok6");
        createDir.mkdir();
//      mambuat file bari di dalam directory
        File myFile = new File(createDir,"myData1.txt");
//      mengeksekusi data yang di tambahkan
        writeData(myFile, info);
        editText.setText("");
    }

    

    public void savePrivate(View view){
        // mengubah type data yang di inputkan menjadi string
        String setData = editText.getText().toString();
        // membuat directory baru
        File folder = getExternalFilesDir("kelompok6");
        // mambuat file bari di dalam directory
        File myFile = new File(folder, "mydata2.txt");
        // mengeksekusi data yang di tambahkan
        writeData(myFile, setData);
        editText.setText("");

     }

//     Fungsi writeData digunakan untuk mengeksekusi data yang sudah di inputkan
    private void writeData(File myFile, String data){
//       Deklarasi variabel yang di gunakan untuk membuat dan menulis data di penyimpanan
        FileOutputStream fileOutputStream = null;
        try {
            System.out.println("TES");
//            membuat berkas baru
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
//            menampilakan lokasi penyimpanan
            Toast.makeText(MainActivity.this, "Done" + myFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}