package com.example.app_medc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//https://www.youtube.com/watch?v=M8sKwoVjqU0
public class EnfermedadesFragment extends Fragment {
    EditText nom_med, ingesta, mg, farmaceutica, padecimiento;
    Button btn_enf_registro;
    private FirebaseAuth firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<EnfermedadesRVModal> enfermedadesList;
    private EnfermedadesAdapter enfermedadesAdapter;
    private RecyclerView enfermedadesRV;
    RecyclerView recyclerView;

    String text;
    int number;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_enfermedades, container, false);
        btn_enf_registro=v.findViewById(R.id.btn_registrar_enf);
        recyclerView=v.findViewById(R.id.recycler_view);

        //INSTANCIA DE USUARIO ACTUAL
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String useruid=user.getUid();

        databaseReference= FirebaseDatabase.getInstance().getReference("Enfermedades");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        enfermedadesList = new ArrayList<>();
        enfermedadesAdapter = new EnfermedadesAdapter(enfermedadesList,this.getContext());
        recyclerView.setAdapter(enfermedadesAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                   EnfermedadesRVModal enfModal = dataSnapshot.getValue((EnfermedadesRVModal.class));
                    if(useruid.equalsIgnoreCase(String.valueOf(enfModal.getUserUD()))) {
                        enfermedadesList.add(enfModal);
                    }

                }
                enfermedadesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btn_enf_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), Register.class));
                System.out.println("BOTON PARA REGISTRAR NUEVO.");
                Intent intent = new Intent(getActivity(), RegistroEnfermedades.class);
                intent.putExtra("some", "Some thing");
                startActivity(intent);
            }
        });


        return v;
    }
}
