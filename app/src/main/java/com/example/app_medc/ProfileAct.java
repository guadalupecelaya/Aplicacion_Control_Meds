package com.example.app_medc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

public class ProfileAct extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText nombre, apaterno, amaterno, correo;
    Button btn_enf_registro;
    FirebaseAuth fAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseFirestore fStore;
    FirebaseUser user;
    private String usuarioID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Intent data = getIntent();

        final String nom = data.getStringExtra("nom");
        String apa=data.getStringExtra("apa");
        String ama = data.getStringExtra("ama");
        String mail =  data.getStringExtra("mail");
        String usuarioID = correo.getText().toString();
        usuarioID=mail;

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();

        nombre=findViewById(R.id.PT_nombre);
        apaterno=findViewById(R.id.PT_ap_paterno);
        amaterno=findViewById(R.id.PT_ap_materno);
        correo=findViewById(R.id.PT_email);


        UsuarioModal  usuario = new UsuarioModal(nom ,apa,
                ama,mail,usuarioID);
        String finaUsuarioID = usuarioID;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("some") != null) {
                Toast.makeText(getApplicationContext(), "data:" + bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }
        }


    }
}
