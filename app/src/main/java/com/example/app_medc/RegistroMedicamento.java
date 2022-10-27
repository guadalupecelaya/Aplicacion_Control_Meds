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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistroMedicamento extends AppCompatActivity {
    private DrawerLayout drawer;
    EditText nom_med, ingesta, mg, farmaceutica, padecimiento;
    Button btn_med_registro;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String medicamentoID;
    private String userID;
    int number;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_med);
        nom_med=findViewById(R.id.PT_nom_medicamento);
        ingesta=findViewById(R.id.PT_via);
        mg=findViewById(R.id.PT_ap_mg);
        farmaceutica=findViewById(R.id.PT_fabricante);
        padecimiento=findViewById(R.id.PT_uso);
        btn_med_registro=findViewById(R.id.btn_registrar_med);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Medicamentos");
        //recyclerView = findViewById(R.id.recycler_view);

        //ID del usuario
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        System.out.println("USERID REGISTRO: "+userID);


        btn_med_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreMedicamento = nom_med.getText().toString();
                String viaAdministracion = ingesta.getText().toString();
                String cantidad = mg.getText().toString();
                String farmaceuticaFabricante = farmaceutica.getText().toString();
                String descripcionUso = padecimiento.getText().toString();
                String medicamentoID = nom_med.getText().toString();
                medicamentoID=nombreMedicamento;
                MedicamentosRVModal medicamentosRVModal = new MedicamentosRVModal(nombreMedicamento,viaAdministracion,
                        cantidad,farmaceuticaFabricante,descripcionUso,medicamentoID, userID);

                //Que??
                String finalMedicamentoID = medicamentoID;
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(finalMedicamentoID).setValue(medicamentosRVModal);
                        Toast.makeText(RegistroMedicamento.this, "Medicamento añadido...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistroMedicamento.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(RegistroMedicamento.this, "Error is"+error.toString(), Toast.LENGTH_SHORT).show();

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
// LISTA DE MEDICAMENTOS
      /* recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(" ENTRA AL CLICK");
                startActivity(new Intent(RegistroMedicamento.this, MainActivity.class));
            }
        });*/


    }
}