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
public class Liquido extends Fragment {
    EditText fecha, hora, evacuacion, cantidad, descripcion;
    Button btn_liq_registro;
    private FirebaseAuth firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference dataReference;
    private ArrayList<LiquidoRVModal> liquidosList;
    private LiquidoAdapter liquidosAdapter;
    private RecyclerView liquidoRV;
    RecyclerView recyclerView;

    String text;
    int number;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_liquidos, container, false);
        btn_liq_registro=v.findViewById(R.id.btn_registrar_liq);
        recyclerView=v.findViewById(R.id.recycler_view);

        //INSTANCIA DE USUARIO ACTUAL
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String useruid=user.getUid();


        databaseReference= FirebaseDatabase.getInstance().getReference("ControlLiquidos");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        liquidosList = new ArrayList<>();
        liquidosAdapter = new LiquidoAdapter(liquidosList,this.getContext());
        recyclerView.setAdapter(liquidosAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    LiquidoRVModal liqModal = dataSnapshot.getValue((LiquidoRVModal.class));
                    if(useruid.equalsIgnoreCase(String.valueOf(liqModal.getUserUD()))){
                        liquidosList.add(liqModal);
                    }


                }
                liquidosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btn_liq_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegistroLiquido.class);
                intent.putExtra("some", "Some thing");
                startActivity(intent);
            }
        });


        return v;
    }
}