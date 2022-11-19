package com.example.app_medc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class RegistroLiquido extends AppCompatActivity {
    private DrawerLayout drawer;
    EditText fecha, hora, evacuacion, descripcion, cantidad;
    Button btn_liq_registro;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String liquidoID;
    private String userID;
    int number;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_liquido);
        fecha=findViewById(R.id.PT_fecha_liq);
        hora=findViewById(R.id.PT_hora_liq);
        evacuacion=findViewById(R.id.PT_evacuacion);
        descripcion=findViewById(R.id.PT_descripcion_liq);
        cantidad=findViewById(R.id.PT_cantidad_liq);
        btn_liq_registro=findViewById(R.id.btn_registrar_liq);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("ControlLiquidos");
        //recyclerView = findViewById(R.id.recycler_view);

        //ID del usuario
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();



        btn_liq_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fechaL = fecha.getText().toString();
                String horaL = hora.getText().toString();
                String evacuacionL = evacuacion.getText().toString();
                String descripcionL = descripcion.getText().toString();
                String cantidadL = cantidad.getText().toString();
                String liquidoID = fecha.getText().toString();
                liquidoID=fechaL;
                LiquidoRVModal liquidosRVModal = new LiquidoRVModal(fechaL,horaL,
                        evacuacionL,descripcionL,cantidadL,liquidoID, userID);

                //Que??
                String finalLiquidoID = liquidoID;
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(finalLiquidoID).setValue(liquidosRVModal);
                        Toast.makeText(RegistroLiquido.this, "Evacuacion añadida...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistroLiquido.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(RegistroLiquido.this, "Error is"+error.toString(), Toast.LENGTH_SHORT).show();

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