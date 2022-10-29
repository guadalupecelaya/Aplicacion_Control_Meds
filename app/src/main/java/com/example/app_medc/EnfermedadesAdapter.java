package com.example.app_medc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EnfermedadesAdapter extends RecyclerView.Adapter<EnfermedadesAdapter.ViewHolder>{
    private ArrayList<EnfermedadesRVModal> enfermedadesRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private EnfermedadesAdapter.enfermedadesClickInterface EnfermedadesClickInterface;


    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nom_enf, fecha, sintoma;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom_enf = itemView.findViewById(R.id.nombre_enf_list);
            fecha=itemView.findViewById(R.id.fecha_list);
            sintoma=itemView.findViewById(R.id.sintomas_list);
        }

    }


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
    public void onBindViewHolder(@NonNull EnfermedadesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        EnfermedadesRVModal enfermedadesRVModal = enfermedadesRVModalArrayList.get(position);
        holder.nombreEnfermedadTV.setText(enfermedadesRVModal.getNombreEnfermedado());
        holder.FechaTV.setText(enfermedadesRVModal.getFechaDiagnostico());
        holder.padecimientoTV.setText(enfermedadesRVModal.getSintomas());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) holder.getAdapterPosition();
                final Intent intent, inte;
                //intent =  new Intent(context, MedicamentoEditar.class);
                intent = new Intent(context, EnfermedadesEditar.class);
                inte = new Intent(context, EnfermedadesEliminar.class);

                intent.putExtra("nombreEnfermedado", enfermedadesRVModalArrayList.get(position).getNombreEnfermedado());
                intent.putExtra("fechaDiagnostico", enfermedadesRVModalArrayList.get(position).getFechaDiagnostico());
                intent.putExtra("sintomas", enfermedadesRVModalArrayList.get(position).getSintomas());
                intent.putExtra("precauciones", enfermedadesRVModalArrayList.get(position).getPrecauciones());
                intent.putExtra("medicamentosComunes", enfermedadesRVModalArrayList.get(position).getMedicamentosComunes());
                intent.putExtra("enfermedadesID", enfermedadesRVModalArrayList.get(position).getEnfermedadesID());

                inte.putExtra("nombreEnfermedado", enfermedadesRVModalArrayList.get(position).getNombreEnfermedado());
                inte.putExtra("fechaDiagnostico", enfermedadesRVModalArrayList.get(position).getFechaDiagnostico());
                inte.putExtra("sintomas", enfermedadesRVModalArrayList.get(position).getSintomas());
                inte.putExtra("precauciones", enfermedadesRVModalArrayList.get(position).getPrecauciones());
                inte.putExtra("medicamentosComunes", enfermedadesRVModalArrayList.get(position).getMedicamentosComunes());
                inte.putExtra("enfermedadesID", enfermedadesRVModalArrayList.get(position).getEnfermedadesID());


                //alerta para editar o eliminar
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());

                // Setting Dialog Title
                alertDialog.setTitle("Opciones: ");

                // Setting Dialog Message
                alertDialog.setMessage("Desea editar o eliminar la enfermedad?");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.button_eliminar);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        context.startActivity(inte);
                        dialog.cancel();
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        context.startActivity(intent);

                    }
                });
                alertDialog.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // code
                        dialog.cancel();
                    }
                });

                // mostrara dialogo
                alertDialog.show();


            }
        });

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

