package com.example.app_medc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MedicamentosAdapter extends RecyclerView.Adapter<MedicamentosAdapter.ViewHolder> {
    private ArrayList<MedicamentosRVModal> medicamentosRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private medicamentosClickInterface MedicamentosClickInterface;



    public MedicamentosAdapter(ArrayList<MedicamentosRVModal> medicamentosRVModalArrayList, medicamentosClickInterface medicamentosClickInterface, Context context) {
        this.medicamentosRVModalArrayList = medicamentosRVModalArrayList;
        this.context=context;
        this.MedicamentosClickInterface = medicamentosClickInterface;
    }

    @NonNull
    @Override
    public MedicamentosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(context).inflate(R.layout.medicamento_rv_item,parent,false);
        return new ViewHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentosAdapter.ViewHolder holder, int position) {
        MedicamentosRVModal medicamentoRVModal = medicamentosRVModalArrayList.get(position);
        holder.nombreMedicamentoTV.setText(medicamentoRVModal.getNombreMedicamento());
        holder.viaAdministracionTV.setText("Rs. "+medicamentoRVModal.getViaAdministracion());
        //Picasso.get().load(medicamentoRVModal.get)

    }

    @Override
    public int getItemCount() {
        return medicamentosRVModalArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreMedicamentoTV, viaAdministracionTV;
        private ImageView medicamentosIV;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nombreMedicamentoTV = itemView.findViewById(R.id.IdIVMedicamento_nombre);
            viaAdministracionTV = itemView.findViewById(R.id.IdIViaMed);
            medicamentosIV=itemView.findViewById(R.id.IdIVMedicamento);

        }
    }

    public interface medicamentosClickInterface{
        void onMedicamentosClick(int position);
    }


}
