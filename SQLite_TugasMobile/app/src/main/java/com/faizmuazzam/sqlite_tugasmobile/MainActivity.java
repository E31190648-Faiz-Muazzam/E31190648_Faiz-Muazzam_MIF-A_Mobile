package com.faizmuazzam.sqlite_tugasmobile;
//memnaggil class java untuk di gunakan pada halaman ini
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
//untuk membuat class Main activity  dengan mewarisi  AppCombatActivity
public class MainActivity extends AppCompatActivity {
    //membuat array string daftar
    String[] daftar;
    //berguna untuk membuat listView01
    ListView ListView01;
    //berguna untuk membuat menu
    Menu menu;
    //berguna untuk memanggil fungsi dari Cursor
    protected Cursor cursor;
    // berguna untuk memanggil kelas DataHelper
    DataHelper dbcenter;
    // berguna untuk memanggil kelas MainActivity
    public static MainActivity ma;

    @Override
//    membuat fungsi onCreate yang di gunakan untuk kondisi awal saat Activity baru diciptakan,
//   biasanya dilakukan inisialisasi pada tahapan ini
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Eksekusi layout yang digunakan pada halaman ini
        setContentView(R.layout.activity_main);
//        Memanggil komponen button yang ada di layout
        Button btn = (Button) findViewById(R.id.button2);
//        membuat fungsi tombol ketika di klik
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
//           Membuat fungsi onClick yang di gunakan untuk membuat fungsi tombol ketika tombol di click
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
//               mengaktifkan fungsi intent yang di gunakan untuk berpindah halaman
                Intent intent = new Intent(MainActivity.this, BuatBiodata.class);
                startActivity(intent);
            }
        });
//        identifikasi variabel ma
        ma = this;
//        identifikasi variabel dbcenter untu menyimpan class DataHelper
        dbcenter = new DataHelper(this);
//        menyegarkan tampilan list
        RefreshList();
    }
    // membuat fungsi RefreshList yang di gunakan untuk menyegarkan tampilan list
    public void RefreshList() {
//        identifikasi variabel db
        SQLiteDatabase db = dbcenter.getReadableDatabase();
//        memanggil query select untuk menampilkan isi data base
        cursor = db.rawQuery("SELECT * FROM biodata", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
//        identifikasi komponen listview
        ListView01 = (ListView) findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
//        membuat fungsi ketika ListView di klik
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //untuk membuat fungsi onItemClick
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
//                membuat dialogitem
                final CharSequence[] dialogitem = {"View Biodata", "Update Biodata", "Delete Biodata"};
//                membuat alert
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                membuat judul alert
                builder.setTitle("Option");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
//                    membuat fungsi onClick ketika komponen dialog item di click
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
//                                ketika item 0 di kilk langsung pindah ke halaman LihatBiodata.java
                                Intent i = new Intent(getApplicationContext(), LihatBiodata.class);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;
                            case 1:
//                                 ketika item 1 di kilk langsung pindah ke halaman UpdateBiodata.java
                                Intent in = new Intent(getApplicationContext(), UpdateBiodata.class);
                                in.putExtra("nama", selection);
                                startActivity(in);
                                break;
//                                 ketika item 2 di kilk maka akan menghapus data terkait pada database
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from biodata where nama = '" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();
    }
}