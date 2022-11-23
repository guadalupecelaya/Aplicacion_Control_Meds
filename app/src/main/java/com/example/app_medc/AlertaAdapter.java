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

public class AlertaAdapter extends RecyclerView.Adapter<AlertaAdapter.ViewHolder> {
    private ArrayList<AlertaRVModal> alertaRVModalArrayList;
    private Context context;
    int lastPos = -1;




    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView hora_alerta, fecha_f_alerta, fecha_i_alerta, enfermedad_alerta, intervalo_alerta, medicamento_alerta;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hora_alerta = itemView.findViewById(R.id.nombre_cuid_list);
            fecha_f_alerta=itemView.findViewById(R.id.telefono_list);
            fecha_i_alerta=itemView.findViewById(R.id.parentesco_list);
            enfermedad_alerta = itemView.findViewById(R.id.nombre_cuid_list);
            medicamento_alerta=itemView.findViewById(R.id.telefono_list);
            intervalo_alerta=itemView.findViewById(R.id.parentesco_list);
        }

    }



    public AlertaAdapter(ArrayList<AlertaRVModal> alertaRVModalArrayList, Context context) {
        this.alertaRVModalArrayList = alertaRVModalArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public AlertaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(context).inflate(R.layout.list_item_alarma,parent,false);
        return new ViewHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull AlertaAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AlertaRVModal alertarRVModal = alertaRVModalArrayList.get(position);
        holder.hora_alerta.setText(alertarRVModal.getHora_inicioA());
        holder.fecha_f_alerta.setText(alertarRVModal.getFecha_finalA());
        holder.fecha_i_alerta.setText(alertarRVModal.getFecha_inicioA());
        holder.medicamento_alerta.setText(alertarRVModal.getMedicamentoA());
        holder.enfermedad_alerta.setText(alertarRVModal.getEnfermedadA());
        holder.intervalo_alerta.setText(alertarRVModal.getIntervaloA());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) holder.getAdapterPosition();
                final Intent intent, inte;
                //EDITAR
                intent = new Intent(context, CuidadorEditar.class);
                intent.putExtra("hora_inicioA", alertaRVModalArrayList.get(position).getHora_inicioA());
                intent.putExtra("fecha_inicioA", alertaRVModalArrayList.get(position).getHora_inicioA());
                intent.putExtra("fecha_finalA", alertaRVModalArrayList.get(position).getFecha_finalA());
                intent.putExtra("medicamentoA", alertaRVModalArrayList.get(position).getMedicamentoA());
                intent.putExtra("enfermedadA", alertaRVModalArrayList.get(position).getEnfermedadA());
                intent.putExtra("intervaloA", alertaRVModalArrayList.get(position).getIntervaloA());

                //ELIMINAR
                inte = new Intent(context, CuidadorEliminar.class);
                inte.putExtra("hora_inicioA", alertaRVModalArrayList.get(position).getHora_inicioA());
                inte.putExtra("fecha_inicioA", alertaRVModalArrayList.get(position).getHora_inicioA());
                inte.putExtra("fecha_finalA", alertaRVModalArrayList.get(position).getFecha_finalA());
                inte.putExtra("medicamentoA", alertaRVModalArrayList.get(position).getMedicamentoA());
                inte.putExtra("enfermedadA", alertaRVModalArrayList.get(position).getEnfermedadA());
                inte.putExtra("intervaloA", alertaRVModalArrayList.get(position).getIntervaloA());


                //alerta para editar o eliminar
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());

                // Setting Dialog Title
                alertDialog.setTitle("Opciones: ");

                // Setting Dialog Message
                alertDialog.setMessage("Desea editar o Eliminar el alerta?");

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
        return alertaRVModalArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView hora_alerta, fecha_f_alerta, fecha_i_alerta, enfermedad_alerta, intervalo_alerta, medicamento_alerta;
        private ImageView cuidadorIV;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            hora_alerta = itemView.findViewById(R.id.hora_alarma_list);
            fecha_f_alerta = itemView.findViewById(R.id.fecha_fin_alarma_list);
            fecha_i_alerta=itemView.findViewById(R.id.fecha_inicio_alarma_list);
            enfermedad_alerta = itemView.findViewById(R.id.padecimiento_alarma_list);
            intervalo_alerta = itemView.findViewById(R.id.tomas_alarma_list);
            medicamento_alerta=itemView.findViewById(R.id.medicamento_alarma_list);

        }
    }

    public interface alertasClickInterface{
        void onAlertasClick(int position);
    }


}