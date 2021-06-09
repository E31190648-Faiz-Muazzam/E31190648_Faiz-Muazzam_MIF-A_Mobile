package com.faizmuazzam.api_valley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class Delete extends AppCompatActivity {
    /**Identifikasi Variabel*/
    EditText deleteID ;
    Button btnDelete;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        /**Menangkap nilai yang di inputkan untuk di simpan pada variabel*/
        deleteID = (EditText) findViewById(R.id.username_param);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        /**Deklarasi Variabel untuk ProgressDialog*/
        pd = new ProgressDialog(Delete.this);
        /**Membuat perintah ketika tombol delete di klik*/
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            /**Fungsi deleteData di eksekusi ketika di klik*/
            public void onClick(View view) {
                deleteData();
            }
        });
    }


    /**Membuat fungsi yang digunakan untuk menghapus data*/
    private void deleteData()
    {
        /**memberikan informasi ketika loading*/
        pd.setMessage("Delete Data ...");
        pd.setCancelable(false);
        pd.show();

        /**Menyambungkan dengan ServerAPI*/
        StringRequest delReq = new StringRequest(Request.Method.POST, ServerAPI.URL_DELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(Delete.this,"pesan : " +res.getString("message"), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(Delete.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                        Toast.makeText(Delete.this, "pesan : gagal menghapus data", Toast.LENGTH_SHORT).show();
                    }
                }){

            /**Melakukan check data dengan username yang di inputkan apakah data yang di inputkan sama denagn yang ada di database*/
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                /**Mengecheck username apakah ada dalam databse*/
                map.put("username",deleteID.getText().toString());
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(delReq);
    }
}