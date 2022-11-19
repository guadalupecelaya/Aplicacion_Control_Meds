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

public class LiquidoEditar extends AppCompatActivity {
    EditText fecha, hora, evacuacion, cantidad, descripcion, liqID;
    Button btn_editar, btn_cancelar;
    ImageButton btnEditarLiq;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RelativeLayout homeRL;
    private String liquidoID;
    private LiquidoRVModal liquidosRVModal;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_liquido);


        String fec = getIntent().getStringExtra("fecha");
        String hor = getIntent().getStringExtra("hora");
        String eva = getIntent().getStringExtra("evacuacion");
        String can = getIntent().getStringExtra("cantidad");
        String des = getIntent().getStringExtra("descripcion");
        liquidoID = getIntent().getStringExtra("hora");


        firebaseDatabase = FirebaseDatabase.getInstance();
        fecha=findViewById(R.id.PT_fecha_liq_ed);
        hora=findViewById(R.id.PT_hora_liq_ed);
        evacuacion=findViewById(R.id.PT_evacuacion_ed);
        descripcion=findViewById(R.id.PT_descripcion_liq_ed);
        cantidad=findViewById(R.id.PT_cantidad_liq_ed);
        btn_editar=findViewById(R.id.btn_registrar_liq_ed);
        btn_cancelar=findViewById(R.id.btn_registrar_liq_cancelar_ed);
        liquidosRVModal=getIntent().getParcelableExtra("ControlLiquidos");

        if(liquidosRVModal!=null){
            fecha.setText(fec);
            hora.setText(hor);
            evacuacion.setText(eva);
            descripcion.setText(des);
            cantidad.setText(can);
        }

        // Editar Temporal
        fecha.setText(fec);
        hora.setText(hor);
        evacuacion.setText(eva);
        descripcion.setText(des);
        cantidad.setText(can);

        databaseReference = firebaseDatabase.getReference("ControlLiquidos").child(liquidoID);
        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fechaL = fecha.getText().toString();
                String horaL = hora.getText().toString();
                String evaluacionL = evacuacion.getText().toString();
                String descripcionL = descripcion.getText().toString();
                String cantidadL = cantidad.getText().toString();
                String liquidoID = hora.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("fecha",fechaL);
                map.put("hora",horaL);
                map.put("evacuacion",evaluacionL);
                map.put("descripcion",descripcionL);
                map.put("cantidad",cantidadL);
                map.put("liquidoID",liquidoID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.updateChildren(map);
                        Toast.makeText(LiquidoEditar.this, "Evacuacion actualizado...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LiquidoEditar.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(LiquidoEditar.this, "Error al actualizar medicamento", Toast.LENGTH_SHORT).show();

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
        Toast.makeText(LiquidoEditar.this, "Evacuacion eliminada", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LiquidoEditar.this, Medicamentos.class));
    }
}
