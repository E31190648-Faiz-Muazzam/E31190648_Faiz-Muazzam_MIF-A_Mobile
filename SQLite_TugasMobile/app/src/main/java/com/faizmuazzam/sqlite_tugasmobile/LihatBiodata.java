package com.faizmuazzam.sqlite_tugasmobile;
//Memanggil class java untuk di gunakan pada halaman ini
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//membuat class Lihat Biodata yang mewarisi class AppCompatActivity dari java
public class LihatBiodata extends AppCompatActivity {
//    Identifikasi variabel
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton2;
    TextView text1, text2, text3, text4, text5;
    @Override
    //membuat fungsi onCreate yang di gunakan untuk kondisi awal saat Activity baru diciptakan,
    //biasanya dilakukan inisialisasi pada tahapan ini
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_biodata);
//        memanggil class data helper
        dbHelper = new DataHelper(this);
//        memanggil komponen yang ada di dalam layout menggunakan ID masing masing komponen
        text1 = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text4 = (TextView) findViewById(R.id.textView4);
        text5 = (TextView) findViewById(R.id.textView5);
//        memanggil query SQL pada class DataHelper
        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Memanggil query select (menampilkan data) berdasarkan nama
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" + getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
//            menampilkan data dalam textview berdasarkan index array
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
        }
//        identifikasi button1
        ton2 = (Button) findViewById(R.id.button1);
//        membuat fungsi pada button1
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
//            mengaktifkan fungsi button1 ketika di klik
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
//                proses selesai dan keluar halaman
                finish();
            }
        });
    }
}