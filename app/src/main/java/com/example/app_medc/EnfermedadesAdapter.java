package com.example.app_medc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EnfermedadesAdapter extends RecyclerView.Adapter<EnfermedadesAdapter.ViewHolder>{
    private ArrayList<EnfermedadesRVModal> enfermedadesRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private EnfermedadesAdapter.enfermedadesClickInterface EnfermedadesClickInterface;



    public EnfermedadesAdapter(ArrayList<EnfermedadesRVModal> enfermedadesRVModalArrayList, Context context) {
        this.enfermedadesRVModalArrayList = enfermedadesRVModalArrayList;
        this.context=context;
        //this.EnfermedadesClickInterface = enfermedadesClickInterface;
    }

    @NonNull
    @Override
    public EnfermedadesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(context).inflate(R.layout.list_item_enf,parent,false);
        return new EnfermedadesAdapter.ViewHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull EnfermedadesAdapter.ViewHolder holder, int position) {
        EnfermedadesRVModal enfermedadesRVModal = enfermedadesRVModalArrayList.get(position);
        holder.nombreEnfermedadTV.setText(enfermedadesRVModal.getNombreEnfermedado());
        holder.FechaTV.setText("Rs. "+enfermedadesRVModal.getFechaDiagnostico());
        holder.padecimientoTV.setText((enfermedadesRVModal.getSintomas()));

        //Picasso.get().load(medicamentoRVModal.get)

    }

    @Override
    public int getItemCount() {
        return enfermedadesRVModalArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreEnfermedadTV, FechaTV, padecimientoTV;
        private ImageView enfermedadesIV;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nombreEnfermedadTV = itemView.findViewById(R.id.nombre_enf_list);
            FechaTV = itemView.findViewById(R.id.fecha_list);
            padecimientoTV =itemView.findViewById(R.id.sintomas_list);

        }
    }

    public interface enfermedadesClickInterface{
        void onEnfermedadesClick(int position);
    }



}

