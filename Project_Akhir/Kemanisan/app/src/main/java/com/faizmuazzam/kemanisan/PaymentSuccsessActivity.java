package com.faizmuazzam.kemanisan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.faizmuazzam.kemanisan.Util.AppController;
import com.faizmuazzam.kemanisan.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PaymentSuccsessActivity extends AppCompatActivity implements View.OnClickListener {
    private Animation btnAnim ;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_payment_succsess);

        btnBack = findViewById(R.id.back_home);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        btnBack.setAnimation(btnAnim);


        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(PaymentSuccsessActivity.this, MainActivity.class);
        startActivity(intent);

    }




}