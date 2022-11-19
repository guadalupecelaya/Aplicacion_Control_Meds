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

public class LiquidoAdapter extends RecyclerView.Adapter<LiquidoAdapter.ViewHolder> {
    private ArrayList<LiquidoRVModal> liquidosRVModalArrayList;
    private Context context;
    int lastPos = -1;

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView fecha, hora, evacuacion;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fecha = itemView.findViewById(R.id.nombre_med_list);
            hora=itemView.findViewById(R.id.farmaceutica_list);
            evacuacion=itemView.findViewById(R.id.mg_list);
        }

    }

    public LiquidoAdapter(ArrayList<LiquidoRVModal> liquidosRVModalArrayList, Context context) {
        this.liquidosRVModalArrayList = liquidosRVModalArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public LiquidoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(context).inflate(R.layout.list_item_liq,parent,false);
        return new ViewHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull LiquidoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LiquidoRVModal liquidoRVModal = liquidosRVModalArrayList.get(position);
        holder.fecha.setText(liquidoRVModal.getFecha());
        holder.hora.setText(liquidoRVModal.getHora());
        holder.evacuacion.setText(liquidoRVModal.getEvacuacion());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) holder.getAdapterPosition();
                final Intent intent, inte;
                intent = new Intent(context, LiquidoEditar.class);
                inte = new Intent(context, LiquidoEliminar.class);


                intent.putExtra("fecha", liquidosRVModalArrayList.get(position).getFecha());
                intent.putExtra("hora", liquidosRVModalArrayList.get(position).getHora());
                intent.putExtra("evacuacion", liquidosRVModalArrayList.get(position).getEvacuacion());
                intent.putExtra("cantidad", liquidosRVModalArrayList.get(position).getCantidad());
                intent.putExtra("descripcion", liquidosRVModalArrayList.get(position).getDescripcion());

                inte.putExtra("fecha", liquidosRVModalArrayList.get(position).getFecha());
                inte.putExtra("hora", liquidosRVModalArrayList.get(position).getHora());
                inte.putExtra("evacuacion", liquidosRVModalArrayList.get(position).getEvacuacion());
                inte.putExtra("cantidad", liquidosRVModalArrayList.get(position).getCantidad());
                inte.putExtra("descripcion", liquidosRVModalArrayList.get(position).getDescripcion());


                //alerta para editar o eliminar
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());

                // Setting Dialog Title
                alertDialog.setTitle("Opciones: ");

                // Setting Dialog Message
                alertDialog.setMessage("Desea editar o eliminar la evacuacion?");

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
        return liquidosRVModalArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView fecha, hora, evacuacion;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            fecha = itemView.findViewById(R.id.fecha_liq_list);
            hora = itemView.findViewById(R.id.hora_liq_list);
            evacuacion=itemView.findViewById(R.id.evacuacion_liq_list);

        }
    }

    public interface liquidosClickInterface{
        void onLiquidosClick(int position);
    }


}