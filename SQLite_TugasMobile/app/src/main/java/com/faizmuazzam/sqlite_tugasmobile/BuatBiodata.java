package com.faizmuazzam.sqlite_tugasmobile;
//memanggil class yang ada di java untuk di gunakan didalam sini
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Membuat BuatBiodata yang mewarisi class AppCompatActivity
public class BuatBiodata extends AppCompatActivity {
//    identifikasi Variabel
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3, text4, text5;
//    membuat fungsi onCreate yang di gunakan untuk kondisi awal saat Activity baru diciptakan,
//    biasanya dilakukan inisialisasi pada tahapan ini
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Eksekusi layout yang digunakan pada halaman ini
        setContentView(R.layout.activity_buat_biodata);
//        memanggil class data helper
        dbHelper= new DataHelper(this);
//        memanggil komponen yang ada di dalam layout menggunakan ID masing masing komponen
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        text5 = (EditText) findViewById(R.id.editText5);
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);
//        mengaktifkan fungsi tombol dengan variabel ton2
        ton1.setOnClickListener(new View.OnClickListener() {
//            Membuat fungsi onClick yang di gunakan untuk membuat fungsi tombol ketika tombol di click
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
//                identifikasi sql pada class dataHelper
                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                mengaktifkan fungsi insert(tambah data)
                db.execSQL("insert into biodata(no, nama, tgl, jk, alamat) values('" +
//                        mengisi data yang ditambahkan berdasarkan apa yang sudah di inputkan user di editText
                        text1.getText().toString()+"','"+
                        text2.getText().toString() +"','" +
                        text3.getText().toString()+"','"+
                        text4.getText().toString() +"','" +
                        text5.getText().toString() + "')");
//                membererikan notifikasi sukses ketika berhasil memnambahkan data
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
//                merefresh tampilan list pada halaman MainActivity
                MainActivity.ma.RefreshList();
//                fungsi selesai dan kembali keluar dari halaman
                finish();
            }
        });
//        mengaktifkan tombol dengan variabel ton2 yang di gunakan untuk mengembalikan halaman semula
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub
                finish();
            }
        });
    }
}