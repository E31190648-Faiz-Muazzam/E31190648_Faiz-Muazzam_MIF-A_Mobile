package com.faizmuazzam.usingpreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {

    private EditText mViewUser, mViewPassword, mViewRepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /** Mengidentifikasi nilai variable yang di dapat dari input Form User, Form Password, dan Form Repassword
        dari Layout RegisterActivity */
        mViewUser =findViewById(R.id.et_userSignup);
        mViewPassword =findViewById(R.id.et_passwordSignup);
        mViewRepassword =findViewById(R.id.et_passwordSignup2);

        /** Eksekusi Method action() dan mengecheck apakah konfirmasi password berhasil
         *  jika tampilan tombol SignUp disentuh */
        mViewRepassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    action();
                    return true;
                }
                return false;
            }
        });
        /** Eksekusi Method action() jika tampilan tombol SignUp disentuh */
        findViewById(R.id.button_signupSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action();
            }
        });

        /** Eksekusi Ke loginActivity jika merasakan tombol SignIp disentuh **/
        findViewById(R.id.button_signupSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),loginActivity.class));
            }
        });
    }

    /** Memeriksa inputan User dan Password dan Memberikan akses ke MainActivity */
    private void action(){
        /* Mereset semua Error dan fokus menjadi default
        * Agar setting eror dapat dilakukan sesuai keinginan tanpa harus keganggu dengan perintah default */
        mViewUser.setError(null);
        mViewPassword.setError(null);
        mViewRepassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        /* Mengambil data dari Form User, Password, Repassword
        * dan mennyimpanya dalam variable baru bertipe String*/
        String repassword = mViewRepassword.getText().toString();
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

        /** Jika form user kosong atau TIDAK memenuhi kriteria di Method cekUser() maka akan muncul Set error
         *  dan pada Form User akan menset variable fokus dan error di Viewnya
         *  serta merubah nilai variabel cancel menjadi true*/
        if (TextUtils.isEmpty(user)){
            mViewUser.setError("This field is required");
            fokus = mViewUser;
            cancel = true;
        /** Kondisi jika data dalam form username sudah ada di dalam penyimpanan Preferences atau tidak memenuhi kriteria */
        }else if(cekUser(user)){
            mViewUser.setError("This Username is already exist");
            fokus = mViewUser;
            cancel = true;
        }

        /** Jika form password kosong dan MEMENUHI kriteria di Method cekPassword maka,
         * Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        /** Kondisi jika form password kosong*/
        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("This field is required");
            fokus = mViewPassword;
            cancel = true;
        /** Kondisi jika data dalam form password sudah di dalam penyimpanan Preferences atau
         * konfirmasi password tidak sama dengan password yang di inputkan sebelumnya */
        }else if (!cekPassword(password,repassword)){
            mViewRepassword.setError("This password is incorrect");
            fokus = mViewRepassword;
            cancel = true;
        }

        /** Jika cancel true, variable fokus mendapatkan fokus.
         * Jika false, maka Kembali ke LoginActivity dan Set User dan Password untuk menyimpan data dalam Preferences */
        if (cancel){
            fokus.requestFocus();
        }else{
            Preferences.setRegisteredUser(getBaseContext(),user);
            Preferences.setRegisteredPass(getBaseContext(),password);
            Toast.makeText(registerActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /** True jika parameter password sama dengan parameter repassword */
    private boolean cekPassword(String password, String repassword){
        return password.equals(repassword);
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}