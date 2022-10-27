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

public class CuidadorEditar extends AppCompatActivity {
    EditText nom_cuid, apa_cuid, ama_cuid, telefono, edad, parentesco, cuidID;
    Button btn_editar_cuid, btn_eliminar_cuid;
    ImageButton btnEditarMed;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RelativeLayout homeRL;
    private String cuidadorID;
    private CuidadorRVModal cuidadorRVModal;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cuid);

        String nombreC = getIntent().getStringExtra("nombreC");
        String apaternoC = getIntent().getStringExtra("apaternoC");
        String amaternoC = getIntent().getStringExtra("amaternoC");
        String edadC = getIntent().getStringExtra("edadC");
        String telefonoC = getIntent().getStringExtra("telefonoC");
        String parentescoC = getIntent().getStringExtra("parentescoC");
        cuidadorID = getIntent().getStringExtra("nombreC");


        firebaseDatabase = FirebaseDatabase.getInstance();
        nom_cuid=findViewById(R.id.PT_nom_cuid);
        apa_cuid=findViewById(R.id.PT_apa_cuid);
        ama_cuid=findViewById(R.id.PT_ama_cuid);
        telefono=findViewById(R.id.PT_telefono_cuid);
        edad=findViewById(R.id.PT_edad_cuid);
        parentesco=findViewById(R.id.PT_parentesco_cuid);
        btn_editar_cuid=findViewById(R.id.btn_editar_cuid);
        btn_eliminar_cuid=findViewById(R.id.btn_eliminar_cuid);
        cuidadorRVModal=getIntent().getParcelableExtra("cuidador");

        if(cuidadorRVModal!=null){
            nom_cuid.setText(nombreC);
            apa_cuid.setText(apaternoC);
            ama_cuid.setText(amaternoC);
            telefono.setText(telefonoC);
            parentesco.setText(parentescoC);
            edad.setText(edadC);
        }
        databaseReference = firebaseDatabase.getReference("Cuidadores").child(cuidadorID);
        btn_editar_cuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NombreCuidador = nom_cuid.getText().toString();
                String ApallidoPaterno = apa_cuid.getText().toString();
                String ApellidoMaterno = ama_cuid.getText().toString();
                String Edad = edad.getText().toString();
                String Parentesco = parentesco.getText().toString();
                String Telefono = telefono.getText().toString();
                String cuidadorID = nom_cuid.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("nombreC",NombreCuidador);
                map.put("apaternoC",ApallidoPaterno);
                map.put("amaternoC",ApellidoMaterno);
                map.put("edadC",Edad);
                map.put("telefonoC",Telefono);
                map.put("parentescoC",Parentesco);
                map.put("cuidadorID",cuidadorID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.updateChildren(map);
                        Toast.makeText(CuidadorEditar.this, "Cuidador actualizado...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CuidadorEditar.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(CuidadorEditar.this, "Error al actualizar cuidador", Toast.LENGTH_SHORT).show();

                    }
                });

                btn_eliminar_cuid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eliminarMedicamento();

                    }
                });
            }


        });

        btn_eliminar_cuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarMedicamento();

            }
        });
    }

    private void eliminarMedicamento(){
        databaseReference.removeValue();
        Toast.makeText(CuidadorEditar.this, "Medicamento eliminado", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(CuidadorEditar.this, MainActivity.class));
    }
}
