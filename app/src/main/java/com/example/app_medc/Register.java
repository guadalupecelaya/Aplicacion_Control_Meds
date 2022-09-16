package com.example.app_medc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText mNombre, mAppaterno, mApmaterno, mEmail, mPassword, mPassword2;
    Button BtnRegistrar;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNombre = findViewById(R.id.PT_nombre);
        mAppaterno = findViewById(R.id.PT_ap_paterno);
        mApmaterno = findViewById(R.id.PT_ap_materno);
        mEmail = findViewById(R.id.PT_email);
        mPassword = findViewById(R.id.PT_password);
        mPassword2 = findViewById(R.id.PT_passwordConfirm);
        mLoginBtn = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();
        BtnRegistrar = findViewById(R.id.btn_registrar);

        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }


        BtnRegistrar.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                mEmail.setError("Correo electronico requerido");
                return;
            }
            if(TextUtils.isEmpty(password)){
                mPassword.setError("contraseña requerida");
                return;
            }
            if(password.length()<6){
                mPassword.setError("La contraseña debe ser mayor o igual a 6 caracteres");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            // REGISTRAR AL USUARIO EN FIREBASE

            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(Register.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        });

        mLoginBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Login.class)));

    }
}