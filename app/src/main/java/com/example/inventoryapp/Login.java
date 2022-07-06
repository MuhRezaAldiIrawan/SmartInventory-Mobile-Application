package com.example.inventoryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    Button clickhereacc, signin;
    EditText emaillogin, passwordlogin;
    String email,password, nama, id, foto;
    ProgressDialog progressDialog;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emaillogin = findViewById(R.id.emailogin);
        passwordlogin = findViewById(R.id.passwordlogin);
        clickhereacc = findViewById(R.id.btn_clickhere1);
        signin = findViewById(R.id.signin_btn);

        sp = getSharedPreferences("userData", MODE_PRIVATE);

        progressDialog = new ProgressDialog(this);

        clickhereacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRegist;

                toRegist = new Intent(Login.this, Registerr.class);
                startActivity(toRegist);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Signing you in...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                email = emaillogin.getText().toString();
                password = passwordlogin.getText().toString();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        validasiData();
                    }
                },1000);
            }
        });
    }

    void validasiData(){
        if(email.equals("") || password.equals("")){
            progressDialog.dismiss();
            Toast.makeText(Login.this, "Periksa kembali data yang anda masukkan !", Toast.LENGTH_SHORT).show();
        }else {
            kirimData();
            getUserData();
        }
    }

    void kirimData(){
        AndroidNetworking.post("https://tkjb2019.com/mobile/api_kelompok_2/sm/login.php")
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
                            Toast.makeText(Login.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                Intent toHome;
                                toHome = new Intent(Login.this, MainActivity.class);
                                startActivity(toHome);
//                                new androidx.appcompat.app.AlertDialog.Builder(Login.this)
//                                        .setMessage("Login Success !")
////                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
////                                            @Override
////                                            public void onClick(DialogInterface dialog, int which) {
////                                                //Intent i = getIntent();
////                                                //setResult(RESULT_CANCELED,i);
////                                                //add_mahasiswa.this.finish();
////
////                                            }
////                                        })
//                                        .setCancelable(false)
//                                        .show();


                            }
                            else{
                                new AlertDialog.Builder(Login.this)
                                        .setMessage("Invalid email and password")
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

    void getUserData(){
        AndroidNetworking.post("https://tkjb2019.com/mobile/api_kelompok_2/sm/getData.php")
                .addBodyParameter("email",""+email)
                .addBodyParameter("password",""+password)
                .setPriority(Priority.MEDIUM)
                .setTag("getData")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("cekLogin",""+response);
                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan = response.getString("result");
                            Log.d("status",""+status);
                            if(status){
                               JSONArray ja = response.getJSONArray("result");
                               Log.d("respon", ""+ja);
                               for(int i = 0; i < ja.length() ; i++){
                                   JSONObject jo = ja.getJSONObject(i);
                                   id = jo.getString("id");
                                   nama = jo.getString("nama");
                                   email = jo.getString("email");
                                   password =jo.getString("password");
                                   foto = jo.getString("foto");
                               }
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("id", id);
                                editor.putString("nama", nama);
                                editor.putString("email", email);
                                editor.putString("password", password);
                                editor.putString("foto", foto);
                                editor.commit();
//                                Toast.makeText(Login.this, ""+nama, Toast.LENGTH_SHORT).show();
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






//    private Boolean validateEmail() {
//        String val = emaillogin.getText().toString();
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//
//        if (val.isEmpty()) {
//            emaillogin.setError("Field cannot be empty");
//            return false;
//        } else if (!val.matches(emailPattern)) {
//            emaillogin.setError("Invalid email address");
//            return false;
//        } else {
//            emaillogin.setError(null);
//            return true;
//        }
//    }



}