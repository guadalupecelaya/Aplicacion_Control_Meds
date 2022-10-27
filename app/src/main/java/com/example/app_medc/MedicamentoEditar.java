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

public class MedicamentoEditar extends AppCompatActivity {
    EditText nom_med, ingesta, mg, farmaceutica, padecimiento, medID;
    Button btn_editar, btn_eliminar;
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

        /*Intent intent = new Intent(getActivity(), MedicamentoEditar.class);
        intent.putExtra("nombre", medicamentosList.get(position).getNombreMedicamento());
        intent.putExtra("viaAdministracion", medicamentosList.get(position).getViaAdministracion());
        intent.putExtra("cantida", medicamentosList.get(position).getCantida());
        intent.putExtra("farmaceuticaFabricante", medicamentosList.get(position).getFarmaceuticaFabricante());
        intent.putExtra("descripcionUso", medicamentosList.get(position).getDescripcionUso());*/

        //Intent intent = new Intent(MainActivity.this,MedicamentoEditar.class);
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
        btn_eliminar=findViewById(R.id.btn_eliminar_cuid);
        medicamentosRVModal=getIntent().getParcelableExtra("medicamento");

        if(medicamentosRVModal!=null){
            /*nom_med.setText(medicamentosRVModal.getNombreMedicamento());
            ingesta.setText(medicamentosRVModal.getViaAdministracion());
            mg.setText(medicamentosRVModal.getCantida());
            farmaceutica.setText(medicamentosRVModal.getFarmaceuticaFabricante());
            padecimiento.setText(medicamentosRVModal.getDescripcionUso());
            medicamentoID=medicamentosRVModal.getMedicamentoID();*/

            nom_med.setText(nombre);
            ingesta.setText(viaAdministracion);
            mg.setText(cantida);
            farmaceutica.setText(farmaceuticaFabricante);
            padecimiento.setText(descripcionUso);
        }
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
                        Toast.makeText(MedicamentoEditar.this, "Medicamento actualizado...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MedicamentoEditar.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MedicamentoEditar.this, "Error al actualizar medicamento", Toast.LENGTH_SHORT).show();

                    }
                });

                btn_eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eliminarMedicamento();

                    }
                });
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
        Toast.makeText(MedicamentoEditar.this, "Medicamento eliminado", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MedicamentoEditar.this, MainActivity.class));
    }
}
