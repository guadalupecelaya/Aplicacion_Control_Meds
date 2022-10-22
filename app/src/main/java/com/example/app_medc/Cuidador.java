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

public class Cuidador extends Fragment {
    EditText nom_cuid, apaterno, amaterno, edad, telefono, parentesco;
    Button btn_cuid_registro;
    String text;
    int number;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cuidadores, container, false);
        btn_cuid_registro=v.findViewById(R.id.btn_registrar_enf);

        btn_cuid_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), Register.class));
                Intent intent = new Intent(getActivity(), RegistroCuidador.class);
                intent.putExtra("some", "Some thing");
                startActivity(intent);
            }
        });


        return v;
    }

}
