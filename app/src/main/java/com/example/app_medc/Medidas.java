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
public class Medidas extends Fragment {
    EditText nom_med, ingesta, mg, farmaceutica, padecimiento;
    Button btn_med_registro;
    private FirebaseAuth firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference dataReference;
    private ArrayList<MedicamentosRVModal> medicamentosList;
    private MedicamentosAdapter medicamentosAdapter;
    private RecyclerView medicamentoRV;
    RecyclerView recyclerView;

    String text;
    int number;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_medicamentos, container, false);
        btn_med_registro=v.findViewById(R.id.btn_registrar_med);
        recyclerView=v.findViewById(R.id.recycler_view);

        //INSTANCIA DE USUARIO ACTUAL
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String useruid=user.getUid();

        System.out.println("USERID: "+useruid);

        databaseReference= FirebaseDatabase.getInstance().getReference("Medicamentos");

        //dataReference= FirebaseDatabase.getInstance().getReference("Medicamentos");
        //dataReference.orderByChild("userUD").equals(useruid);
        //databaseReference=FirebaseDatabase.getInstance().getReference("Medicamentos").child().child(useruid);

        //System.out.println("MED MODAL:");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        medicamentosList = new ArrayList<>();
        medicamentosAdapter = new MedicamentosAdapter(medicamentosList,this.getContext());
        recyclerView.setAdapter(medicamentosAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MedicamentosRVModal medModal = dataSnapshot.getValue((MedicamentosRVModal.class));
                    System.out.println("MED MODAL: "+ medModal.getUserUD()+"  -----------USERUID: "+ useruid);
                    if(useruid.equalsIgnoreCase(String.valueOf(medModal.getUserUD()))){
                        System.out.println("ESTA DENTRO DEL IF");
                        medicamentosList.add(medModal);
                    }


                }
                medicamentosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btn_med_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegistroMedicamento.class);
                intent.putExtra("some", "Some thing");
                startActivity(intent);
            }
        });


        return v;
    }
}