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

public class EnfermedadesEditar extends AppCompatActivity {
    EditText nom_enf, sintoma, precaucion, medComunes, FechaDiagnostico, enfID;
    Button btn_editar, btn_cancelar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String medicamentoID;
    private MedicamentosRVModal medicamentosRVModal;

    /*
    * nombreEnfermedado = in.readString();
        fechaDiagnostico = in.readString();
        sintomas = in.readString();
        precauciones = in.readString();
        medicamentosComunes = in.readString();
        enfermedadesID = in.readString();
        userUD = in.readString();
    * */


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_enf);

        String nombreEnf = getIntent().getStringExtra("nombreEnfermedado");
        String sintom = getIntent().getStringExtra("sintomas");
        String prec = getIntent().getStringExtra("precauciones");
        String mComun = getIntent().getStringExtra("medicamentosComunes");
        String FD = getIntent().getStringExtra("fechaDiagnostico");
        String enfermedadID = getIntent().getStringExtra("enfermedadesID");
        medicamentoID = getIntent().getStringExtra("nombreEnfermedado");


        firebaseDatabase = FirebaseDatabase.getInstance();
        nom_enf=findViewById(R.id.PT_nom_enfermedad_ed);
        sintoma=findViewById(R.id.PT_sintomas_ed);
        precaucion=findViewById(R.id.PT_precausiones_ed);
        medComunes=findViewById(R.id.PT_medicamentos_comunes_ed);
        FechaDiagnostico=findViewById(R.id.PT_fecha_diagnostico_ed);
        btn_editar=findViewById(R.id.btn_editar_enf);
        btn_cancelar=findViewById(R.id.btn_cancelar_enf);
        medicamentosRVModal=getIntent().getParcelableExtra("medicamento");

        if(medicamentosRVModal!=null){
            nom_enf.setText(nombreEnf);
            sintoma.setText(sintom);
            precaucion.setText(prec);
            medComunes.setText(mComun);
            FechaDiagnostico.setText(FD);
        }

        // Editar Temporal
        nom_enf.setText(nombreEnf);
        sintoma.setText(sintom);
        precaucion.setText(prec);
        medComunes.setText(mComun);
        FechaDiagnostico.setText(FD);

        databaseReference = firebaseDatabase.getReference("Enfermedades").child(enfermedadID);
        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreEnf = nom_enf.getText().toString();
                String sintom = sintoma.getText().toString();
                String prec = precaucion.getText().toString();
                String mComun = medComunes.getText().toString();
                String FD = FechaDiagnostico.getText().toString();
                String enfermedadID = nom_enf.getText().toString();


                Map<String,Object> map = new HashMap<>();
                map.put("nombreEnfermedado",nombreEnf);
                map.put("sintomas",sintom);
                map.put("precauciones",prec);
                map.put("medicamentosComunes",mComun);
                map.put("fechaDiagnostico",FD);
                map.put("enfermedadesID",enfermedadID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.updateChildren(map);
                        Toast.makeText(EnfermedadesEditar.this, "Enfermedad actualizada...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EnfermedadesEditar.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EnfermedadesEditar.this, "Error al actualizar enfermedad", Toast.LENGTH_SHORT).show();

                    }
                });

                btn_cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eliminarEnfermedad();

                    }
                });
            }


        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarEnfermedad();

            }
        });
    }

    private void eliminarEnfermedad(){
        Toast.makeText(EnfermedadesEditar.this, "Medicamento eliminado", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EnfermedadesEditar.this, MainActivity.class));
    }
}
