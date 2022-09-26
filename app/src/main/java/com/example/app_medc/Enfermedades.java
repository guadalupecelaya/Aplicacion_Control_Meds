package com.example.app_medc;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Enfermedades extends Fragment {
    EditText nom_enf, fecha, sintom, precauciones, medicamentos;
    Button btn_enf_registro;
    String text;
    int number;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_enfermedades, container, false);
        btn_enf_registro=v.findViewById(R.id.btn_registrar_enf);

        btn_enf_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), Register.class));
                Intent intent = new Intent(getActivity(), RegistroEnfermedades.class);
                intent.putExtra("some", "Some thing");
                startActivity(intent);
            }
        });


        return v;
    }
    
}
