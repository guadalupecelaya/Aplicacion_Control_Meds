package com.example.app_medc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistroCuidador extends AppCompatActivity {
    private DrawerLayout drawer;
    EditText nom_cuid, apa_cuid, ama_cuid, edad_cuid, telefono_cuid, parentesco_cuid, cuidador_id;
    Button btn_cuid_registro;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String cuidadorID;
    int number;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuid);
        nom_cuid=findViewById(R.id.PT_nom_cuidador);
        apa_cuid=findViewById(R.id.PT_apaterno_cuidador);
        ama_cuid=findViewById(R.id.PT_amaterno_cuidador);
        edad_cuid=findViewById(R.id.PT_edad_cuidador);
        telefono_cuid=findViewById(R.id.PT_Telefono_cuidador);
        parentesco_cuid=findViewById(R.id.PT_relacion_cuidador);
        btn_cuid_registro=findViewById(R.id.btn_registrar_cuidador);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Cuidadores");


        recyclerView = findViewById(R.id.recycler_view);



        btn_cuid_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NombreCuidador = nom_cuid.getText().toString();
                String ApallidoPaterno = apa_cuid.getText().toString();
                String ApellidoMaterno = ama_cuid.getText().toString();
                String Edad = edad_cuid.getText().toString();
                String Parentesco = parentesco_cuid.getText().toString();
                String Telefono = telefono_cuid.getText().toString();
                String cuidadorID = NombreCuidador;
                cuidadorID=NombreCuidador;
                CuidadorRVModal cuidadorRVModal = new CuidadorRVModal(NombreCuidador,ApallidoPaterno,ApellidoMaterno,
                        Edad,Parentesco,Telefono,cuidadorID);

                //Que??
                String finalCuidadorID = cuidadorID;
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(finalCuidadorID).setValue(cuidadorRVModal);
                        Toast.makeText(RegistroCuidador.this, "Cuidador añadido...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistroCuidador.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(RegistroCuidador.this, "Error is"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("some") != null) {
                Toast.makeText(getApplicationContext(), "data:" + bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }
        }
        System.out.println(" ANTES DEL CLICK");



    }
}