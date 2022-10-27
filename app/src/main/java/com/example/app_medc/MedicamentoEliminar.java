package com.example.app_medc;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MedicamentoEliminar extends AppCompatActivity {

    EditText nom_med, ingesta, mg, farmaceutica, padecimiento, medID;
    Button btn_editar, btn_eliminar;
    ImageButton btnEditarMed;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RelativeLayout homeRL;
    Context context;
    private String medicamentoID;
    private MedicamentosRVModal medicamentosRVModal;

    //  Firebase Variables para diferenciar log
    FirebaseUser firebaseUser;
    FirebaseDatabase firebase_Database;
    DatabaseReference firebaseDatabaseReference;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_med);

        Log.e("activity_editar_med", getLocalClassName());

        //  Inicializa las variables
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabaseReference = firebaseDatabase.getReference();

        String nombre = getIntent().getStringExtra("nombre");
        String viaAdministracion = getIntent().getStringExtra("viaAdministracion");
        String cantida = getIntent().getStringExtra("cantida");
        String farmaceuticaFabricante = getIntent().getStringExtra("farmaceuticaFabricante");
        String descripcionUso = getIntent().getStringExtra("descripcionUso");
        medicamentoID = getIntent().getStringExtra("nombre");
        System.out.println("LINEA PARA VER NOMBRE: "+nombre);

        firebaseDatabase = FirebaseDatabase.getInstance();
        nom_med=findViewById(R.id.PT_nom_medicamento);
        ingesta=findViewById(R.id.PT_via);
        mg=findViewById(R.id.PT_ap_mg);
        farmaceutica=findViewById(R.id.PT_fabricante);
        padecimiento=findViewById(R.id.PT_uso);
        btn_editar=findViewById(R.id.btn_editar_cuid);
        btn_eliminar=findViewById(R.id.btn_eliminar_cuid);
        medicamentosRVModal=getIntent().getParcelableExtra("medicamento");


        if(medicamentosRVModal!=null){

            nom_med.setText(nombre);
            ingesta.setText(viaAdministracion);
            mg.setText(cantida);
            farmaceutica.setText(farmaceuticaFabricante);
            padecimiento.setText(descripcionUso);
        }
        databaseReference = firebaseDatabase.getReference("Medicamentos").child(medicamentoID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eliminarMedicamento();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MedicamentoEliminar.this, "Error al actualizar medicamento", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void eliminarMedicamento(){
        databaseReference.removeValue();
        Toast.makeText(MedicamentoEliminar.this, "Medicamento eliminado", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MedicamentoEliminar.this, MainActivity.class));
        finish();

    }


}

