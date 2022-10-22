package com.example.app_medc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistroEnfermedades extends AppCompatActivity {
    private DrawerLayout drawer;
    EditText nom_enf, fechaD, sintom, precauciones, medicamentos;
    Button btn_enf_registro;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String enfermedadID;
    int number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_enf);
        nom_enf=findViewById(R.id.PT_nom_enfermedad);
        fechaD=findViewById(R.id.PT_fecha_diagnostico);
        sintom=findViewById(R.id.PT_Sintomas);
        precauciones=findViewById(R.id.PT_precauciones);
        medicamentos=findViewById(R.id.PT_medicamentos_comunes);
        btn_enf_registro=findViewById(R.id.btn_registrar_enf);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Enfermedades");

        btn_enf_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreEnfermedad = nom_enf.getText().toString();
                String fecha_diagnostico=fechaD.getText().toString();
                String sintomas = sintom.getText().toString();
                String cuidados =  precauciones.getText().toString();
                String medicamentosComunes = medicamentos.getText().toString();
                String enfermedadID = nom_enf.getText().toString();
                enfermedadID=nombreEnfermedad;

                EnfermedadesRVModal  enfermedadesRVModal = new EnfermedadesRVModal(nombreEnfermedad ,fecha_diagnostico,
                        sintomas,cuidados,medicamentosComunes,enfermedadID);

                //Que??
                String finalEnfermedadID = enfermedadID;
                //añadir nuevas enfermedades
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(finalEnfermedadID).setValue(enfermedadesRVModal);
                        Toast.makeText(RegistroEnfermedades.this, "Enfermedad registrada...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistroEnfermedades.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(RegistroEnfermedades.this, "Error is"+error.toString(), Toast.LENGTH_SHORT).show();

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
    }
}