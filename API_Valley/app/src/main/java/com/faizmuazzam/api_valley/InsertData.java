package com.faizmuazzam.api_valley;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.faizmuazzam.api_valley.Util.AppController;
import com.faizmuazzam.api_valley.Util.ServerAPI;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertData extends AppCompatActivity {
    /**Identifikasi Variabel*/
    EditText username,grub,nama, password;
    Button btnbatal,btnsimpan;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_username = data.getStringExtra("username");
        String intent_grup = data.getStringExtra("grub");
        String intent_nama = data.getStringExtra("nama");
        String intent_password = data.getStringExtra("password");
        /*end get data from intent*/
        /**Menangkap nilai yang di inputkan untuk di simpan pada variabel*/
        username = (EditText) findViewById(R.id.inp_username);
        grub = (EditText) findViewById(R.id.inp_grup);
        nama = (EditText) findViewById(R.id.inp_nama);
        password = (EditText) findViewById(R.id.inp_password);
        btnbatal = (Button) findViewById(R.id.btn_cancel);
        btnsimpan = (Button) findViewById(R.id.btn_simpan);
        /**Deklarasi Variabel untuk ProgressDialog*/
        pd = new ProgressDialog(InsertData.this);

        /**kondisi update / insert*/
        if(update == 1)
        {
            btnsimpan.setText("Update Data");
            username.setText(intent_username);
            username.setVisibility(View.GONE);
            grub.setText(intent_grup);
            nama.setText(intent_nama);
            password.setText(intent_password);

        }

        /**Membuat perintah ketika tombol simpan di klik*/
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(update == 1)
                {
                    Update_data();
                }else {
                    simpanData();
                }
            }
        });
        /**Membuat perintah ketika tombol batal di klik*/
        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(InsertData.this,MainActivity.class);
                startActivity(main);
            }
        });
    }
    /**Membuat fungsi yang digunakan untuk mengubah data*/
    private void Update_data()
    {
        /**memberikan informasi ketika loading*/
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertData.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertData.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertData.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            /**Mengubah data sesuai dengan kolom data base yang tersimpan*/
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("username",username.getText().toString());
                map.put("grub",grub.getText().toString());
                map.put("nama",nama.getText().toString());
                map.put("password",password.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);
    }


    /**Membuat fungsi yang digunakan untuk menambahkan data*/
    private void simpanData()
    {
        /**memberikan informasi ketika loading*/
        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertData.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertData.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertData.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            /**Menambahkan data sesuai dengan kolom yang ada di data base*/
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("username",username.getText().toString());
                map.put("grub",grub.getText().toString());
                map.put("nama",nama.getText().toString());
                map.put("password",password.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}