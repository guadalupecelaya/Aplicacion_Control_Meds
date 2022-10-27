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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cuidador extends Fragment {
    EditText nom_cuid, apaterno, amaterno, edad, telefono, parentesco;
    Button btn_registrar_cuid;
    private FirebaseAuth firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<CuidadorRVModal> cuidadorList;
    private CuidadorAdapter cuidadorAdapter;
    private RecyclerView cuidadorRV;
    RecyclerView recyclerView;
    String text;
    int number;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cuidadores, container, false);
        btn_registrar_cuid=v.findViewById(R.id.btn_registrar_cuid_mas);
        recyclerView=v.findViewById(R.id.recycler_view);

        databaseReference= FirebaseDatabase.getInstance().getReference("Cuidadores");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        cuidadorList = new ArrayList<>();
        cuidadorAdapter = new CuidadorAdapter(cuidadorList,this.getContext());
        recyclerView.setAdapter(cuidadorAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    CuidadorRVModal cuidModal = dataSnapshot.getValue((CuidadorRVModal.class));
                    cuidadorList.add(cuidModal);

                }
                cuidadorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btn_registrar_cuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), Register.class));
                System.out.println("BOTON PARA REGISTRAR NUEVO.");
                Intent intent = new Intent(getActivity(), RegistroCuidador.class);
                intent.putExtra("some", "Some thing");
                startActivity(intent);
            }
        });


        return v;
    }
}