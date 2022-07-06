package com.example.inventoryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.ETC1Util;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.RenderScript;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

public class Registerr extends AppCompatActivity {

    Button clickhere, createacc;
    EditText ETSU1, ETSU2, ETSU3, ETSU4;
    String username,email,password,re_type_password;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerr);


        ETSU1 = findViewById(R.id.usernameregis);
        ETSU2 = findViewById(R.id.emailregis);
        ETSU3 = findViewById(R.id.passwordregis);
        ETSU4 = findViewById(R.id.retypepassregis);
        createacc = findViewById(R.id.create_btn);
        clickhere = findViewById(R.id.btn_clickhere);

        progressDialog = new ProgressDialog(this);

        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Signing you up...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                username = ETSU1.getText().toString();
                email = ETSU2.getText().toString();
                password = ETSU3.getText().toString();
                re_type_password = ETSU4.getText().toString();



                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        validasiData();
                    }
                },1000);

            }
        });

        clickhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin;
                toLogin = new Intent(Registerr.this, Login.class);
                startActivity(toLogin);
            }
        });

    }

    void validasiData(){
        if(username.equals("") || email.equals("") || password.equals("") || re_type_password.equals("")){
            progressDialog.dismiss();
            Toast.makeText(Registerr.this, "Periksa kembali data yang anda masukkan !", Toast.LENGTH_SHORT).show();
        }else {
            kirimData();
        }
    }



    private Boolean validateUsername(){
        String val = ETSU1.getText().toString().trim();

        if (val.isEmpty()){
            ETSU1.setError("Field must be filled");
            return false;
        }else{
            ETSU1.setError(null);

            return true;
        }
    }

    private Boolean validateEmail() {
        String val = ETSU2.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            ETSU2.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            ETSU2.setError("Invalid email address");
            return false;
        } else {
            ETSU2.setError(null);
            return true;
        }
    }


    private Boolean validatePassword() {
        String val = ETSU3.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=-])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            ETSU3.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            ETSU3.setError("Password is too weak");
            return false;
        } else {
            ETSU3.setError(null);

            return true;
        }
    }

//    public void registerUser(View view) {
//
//        username = ETSU1.getEditText().getText().toString();
//        email = ETSU2.getEditText().getText().toString();
//        password = ETSU3.getEditText().getText().toString();
//        re_type_password = ETSU4.getEditText().getText().toString();
//
//        Intent toMain;
//        toMain = new Intent(getApplicationContext(), MainActivity.class);
//        toMain.putExtra("ETSU1",username);
//
//        startActivity(toMain);
//
//    };

    void kirimData(){
        AndroidNetworking.post("https://tkjb2019.com/mobile/api_kelompok_2/sm/testinguser.php")
                .addBodyParameter("nama",""+username)
                .addBodyParameter("email",""+email)
                .addBodyParameter("password",""+password)
                .setPriority(Priority.MEDIUM)
                .setTag("Tambah Data")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("cekTambah",""+response);
                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan = response.getString("result");
                            Toast.makeText(Registerr.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new androidx.appcompat.app.AlertDialog.Builder(Registerr.this)
                                        .setMessage("You've been registered!")
                                        .setCancelable(false)
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
//                                                Intent i = getIntent();
//                                                setResult(RESULT_OK,i);
//                                                add_mahasiswa.this.finish();
                                                startActivity(new Intent( Registerr.this, Login.class));
                                            }
                                        })
                                        .show();
                            }
                            else{
                                new AlertDialog.Builder(Registerr.this)
                                        .setMessage("Gagal Menambahkan Data !")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //Intent i = getIntent();
                                                //setResult(RESULT_CANCELED,i);
                                                //add_mahasiswa.this.finish();
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorTambahData",""+anError.getErrorBody());
                    }
                });
    }


}