package com.example.app_medc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Medicamentos extends Fragment {
    EditText nom_med, ingesta, mg, farmaceutica, padecimiento;
    Button btn_med_registro;
    private FirebaseAuth firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<MedicamentosRVModal> medicamentosRVModalArrayList;
    private MedicamentosAdapter medicamentosAdapter;
    private RecyclerView medicamentoRV;

    String text;
     int number;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_medicamentos, container, false);
        btn_med_registro=v.findViewById(R.id.btn_registrar_med);

        btn_med_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), Register.class));
                Intent intent = new Intent(getActivity(), RegistroMedicamento.class);
                intent.putExtra("some", "Some thing");
                startActivity(intent);
            }
        });


        return v;
    }
}

