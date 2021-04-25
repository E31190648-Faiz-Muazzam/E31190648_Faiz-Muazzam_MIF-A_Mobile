package com.faizmuazzam.sqlite_tugasmobile;
//Memanggil class java untuk di gunakan pada halaman ini
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//Membuat UpdateBiodata yang mewarisi class AppCompatActivity
public class UpdateBiodata extends AppCompatActivity {
//    Identifikasi variabel
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3, text4, text5;
    @Override
//    membuat fungsi onCreate yang di gunakan untuk kondisi awal saat Activity baru diciptakan,
//    biasanya dilakukan inisialisasi pada tahapan ini
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        memanggil komponen yang ada di dalam layout menggunakan ID masing masing komponen
        setContentView(R.layout.activity_update_biodata);
//        memanggil class data helper
        dbHelper= new DataHelper(this);
//        memanggil komponen yang ada di dalam layout menggunakan ID masing masing komponen
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        text5 = (EditText) findViewById(R.id.editText5);
//        memanggil query SQL pada class DataHelper
        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Memanggil query select (menampilkan data) berdasarkan nama
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
//            menampilkan data dalam textview berdasarkan index array
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
        }
//        identifikasi button1
        ton1 = (Button) findViewById(R.id.button1);
//        identifikasi button2
        ton2 = (Button) findViewById(R.id.button2);
//       membuat fungsi pada button1
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
//          mengaktifkan fungsi button1 ketika di klik
            public void onClick(View arg0) {
// TODO Auto-generated method stub
//                identivfikasi sintaq SQL pada class DataHelper
                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                identitifikasi query update(ubah data) berdasarkan nama
                db.execSQL("update biodata set nama='"+
                        text2.getText().toString() +"', tgl='" +
                        text3.getText().toString()+"', jk='"+
                        text4.getText().toString() +"', alamat='" +
                        text5.getText().toString() + "' where no='" +
                        text1.getText().toString()+"'");
//                membuat nitifikasi berhasil
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
//                menyegarkan tampilan list
                MainActivity.ma.RefreshList();
//                proses selesai dan keluar dari halaman
                finish();
            }
        });
//        membuat fungsi untuk button 2
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
//            identifikasi fungsi ketika button 2 di klik
            public void onClick(View arg0) {
// TODO Auto-generated method stub
//                proses selesai dan keluar dari halaman
                finish();
            }
        });
    }
}