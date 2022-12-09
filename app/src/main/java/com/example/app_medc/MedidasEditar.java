package com.example.app_medc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MedidasEditar extends AppCompatActivity {
    EditText nom_med, ingesta, mg, farmaceutica, padecimiento, medID;
    Button btn_editar, btn_cancelar;
    ImageButton btnEditarMed;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RelativeLayout homeRL;
    private String medicamentoID;
    private MedicamentosRVModal medicamentosRVModal;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_med);

        String nombre = getIntent().getStringExtra("nombre");
        String viaAdministracion = getIntent().getStringExtra("viaAdministracion");
        String cantida = getIntent().getStringExtra("cantida");
        String farmaceuticaFabricante = getIntent().getStringExtra("farmaceuticaFabricante");
        String descripcionUso = getIntent().getStringExtra("descripcionUso");
        medicamentoID = getIntent().getStringExtra("nombre");


        firebaseDatabase = FirebaseDatabase.getInstance();
        nom_med=findViewById(R.id.PT_nom_medicamento);
        ingesta=findViewById(R.id.PT_via);
        mg=findViewById(R.id.PT_ap_mg);
        farmaceutica=findViewById(R.id.PT_fabricante);
        padecimiento=findViewById(R.id.PT_uso);
        btn_editar=findViewById(R.id.btn_editar_cuid);
        btn_cancelar=findViewById(R.id.btn_cancelar_cuid);
        medicamentosRVModal=getIntent().getParcelableExtra("medicamento");

        if(medicamentosRVModal!=null){
            nom_med.setText(nombre);
            ingesta.setText(viaAdministracion);
            mg.setText(cantida);
            farmaceutica.setText(farmaceuticaFabricante);
            padecimiento.setText(descripcionUso);
        }

        // Editar Temporal
        nom_med.setText(nombre);
        ingesta.setText(viaAdministracion);
        mg.setText(cantida);
        farmaceutica.setText(farmaceuticaFabricante);
        padecimiento.setText(descripcionUso);

        System.out.println("LINEA PARA VER trae el ID: "+medicamentoID);
        databaseReference = firebaseDatabase.getReference("Medicamentos").child(medicamentoID);
        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreMedicamento = nom_med.getText().toString();
                String viaAdministracion = ingesta.getText().toString();
                String cantidad = mg.getText().toString();
                String farmaceuticaFabricante = farmaceutica.getText().toString();
                String descripcionUso = padecimiento.getText().toString();
                String medicamentoID = nom_med.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("nombreMedicamento",nombreMedicamento);
                map.put("viaAdministracion",viaAdministracion);
                map.put("cantida",cantidad);
                map.put("farmaceuticaFabricante",farmaceuticaFabricante);
                map.put("descripcionUso",descripcionUso);
                map.put("medicamentoID",medicamentoID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.updateChildren(map);
                        Toast.makeText(MedidasEditar.this, "Medicamento actualizado...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MedidasEditar.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MedidasEditar.this, "Error al actualizar medicamento", Toast.LENGTH_SHORT).show();

                    }
                });

                btn_cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eliminarMedicamento();

                    }
                });
            }


        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarMedicamento();

            }
        });
    }



    private void eliminarMedicamento(){
        databaseReference.removeValue();
        Toast.makeText(MedidasEditar.this, "Medicamento eliminado", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MedidasEditar.this, Medicamentos.class));
    }
}
