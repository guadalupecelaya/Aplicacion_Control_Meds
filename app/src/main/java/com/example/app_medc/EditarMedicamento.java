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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditarMedicamento extends AppCompatActivity {
    EditText nom_med, ingesta, mg, farmaceutica, padecimiento;
    Button btn_editar, btn_eliminar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String medicamentoID;
    private MedicamentosRVModal medicamentosRVModal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
        nom_med=findViewById(R.id.PT_nom_medicamento);
        ingesta=findViewById(R.id.PT_via);
        mg=findViewById(R.id.PT_ap_mg);
        farmaceutica=findViewById(R.id.PT_fabricante);
        padecimiento=findViewById(R.id.PT_uso);
        btn_editar=findViewById(R.id.btn_editar_med);
        btn_eliminar=findViewById(R.id.btn_eliminar_med);
        medicamentosRVModal=getIntent().getParcelableExtra("medicamento");
        if(medicamentosRVModal!=null){
            nom_med.setText(medicamentosRVModal.getNombreMedicamento());
            ingesta.setText(medicamentosRVModal.getViaAdministracion());
            mg.setText(medicamentosRVModal.getCantida());
            farmaceutica.setText(medicamentosRVModal.getFarmaceuticaFabricante());
            padecimiento.setText(medicamentosRVModal.getDescripcionUso());
            medicamentoID=medicamentosRVModal.getMedicamentoID();
        }


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
                        Toast.makeText(EditarMedicamento.this, "Medicamento actualizado...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditarMedicamento.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditarMedicamento.this, "Error al actualizar medicamento", Toast.LENGTH_SHORT).show();

                    }
                });

                btn_eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eliminarMedicamento();

                    }
                });
            }

            private void eliminarMedicamento(){
                databaseReference.removeValue();
                Toast.makeText(EditarMedicamento.this, "Medicamento eliminado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditarMedicamento.this, MainActivity.class));
            }
        }
        );
    }
}
