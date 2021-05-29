package com.faizmuazzam.usingpreferences;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    private EditText mViewUser, mViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /** Mengidentifikasi variable dengan editText User dan Form Password yang di inputkan dari Layout LoginActivity **/
        mViewUser=findViewById(R.id.et_userSignin);
        mViewPassword =findViewById(R.id.et_passwordSignin);
        /** Eksekusi Method action() Jika tombol SignIn di keyboard di sentuh **/
        mViewPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    action();
                    return true;
                }
                return false;
            }
        });

        /** Menjalankan Method action() jika merasakan tombol SignIn disentuh **/
        findViewById(R.id.button_signinSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action();
            }
        });
        /** Ke RegisterActivity jika merasakan tombol SignUp disentuh **/
        findViewById(R.id.button_signupSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),registerActivity.class));
            }
        });
    }

    /** pada MainActivity jika data Status Login dari Data Preferences bernilai true
     * kesimpulannya jika status login bernilai true maka kembali ke halaman MainActivity**/
    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        }
    }

    /** Membuat method action untuk meng-check inputan User dan Password dan Memberikan status login dan akses ke MainActivity **/
    private void action(){
        /* Mereset semua Error dan fokus menjadi default
        * Agar setting eror dapat dilakukan sesuai keinginan tanpa harus keganggu dengan perintah default*/
        mViewUser.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        /** Mengambil text dari editText form User dan editText form Password
         * dan mennyimpanya dalam variable baru bertipe String **/
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

        /** Jika form user kosong atau TIDAK memenuhi kriteria di Method cekUser() maka akan muncul Set error
         *  dan pada Form User akan menset variable fokus dan error di Viewnya
         *  serta merubah nilai variabel cancel menjadi true **/
        /** Kondisi jika form username kosong*/
        if (TextUtils.isEmpty(user)){
            mViewUser.setError("This field is required");
            fokus = mViewUser;
            cancel = true;
        /** Kondisi jika data dalam form username tidak ada di dalam penyimpanan Preferences atau tidak memenuhi kriteria */
        }else if(!cekUser(user)){
            mViewUser.setError("This Username is not found");
            fokus = mViewUser;
            cancel = true;
        }

        /** Sama syarat percabangannya dengan User seperti di atas. Bedanya ini untuk Form Password
         * Ada check konfirmasi pasword**/
        /** Kondisi jika form password kosong */
        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("This field is required");
            fokus = mViewPassword;
            cancel = true;
        /** Kondisi jika data dalam form password tidak ada di dalam penyimpanan Preferences atau tidak memenuhi kriteria */
        }else if (!cekPassword(password)){
            mViewPassword.setError("This password is incorrect");
            fokus = mViewPassword;
            cancel = true;
        }

        /** Jika cancel true, variable fokus mendapatkan fokus **/
        if (cancel) fokus.requestFocus();
        else login();
    }

    /** Menuju ke MainActivity dan Set User dan Status menjadi sedang sedang login dalam Preferences **/
    private void login(){
        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(),true);
        Toast.makeText(loginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getBaseContext(),MainActivity.class));finish();
    }

    /** True jika parameter password sama dengan data password yang terdaftar dari Preferences **/
    private boolean cekPassword(String password){
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences **/
    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}