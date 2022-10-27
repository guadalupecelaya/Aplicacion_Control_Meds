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

public class CuidadorAdapter extends RecyclerView.Adapter<CuidadorAdapter.ViewHolder> {
    private ArrayList<CuidadorRVModal> cuidadorRVModalArrayList;
    private Context context;
    int lastPos = -1;




    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nom_cuidador, telefono, parentesco;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom_cuidador = itemView.findViewById(R.id.nombre_cuid_list);
            telefono=itemView.findViewById(R.id.telefono_list);
            parentesco=itemView.findViewById(R.id.parentesco_list);
        }





    }



    public CuidadorAdapter(ArrayList<CuidadorRVModal> cuidadorRVModalArrayList, Context context) {
        this.cuidadorRVModalArrayList = cuidadorRVModalArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public CuidadorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(context).inflate(R.layout.list_item_cuid,parent,false);
        return new ViewHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull CuidadorAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CuidadorRVModal cuidadorRVModal = cuidadorRVModalArrayList.get(position);
        holder.nombreCuidador.setText(cuidadorRVModal.getNombreC());
        holder.telefono.setText(cuidadorRVModal.getTelefonoC());
        holder.parentesco.setText(cuidadorRVModal.getParentescoC());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) holder.getAdapterPosition();
                final Intent intent, inte;
                //EDITAR
                intent = new Intent(context, CuidadorEditar.class);
                intent.putExtra("nombreC", cuidadorRVModalArrayList.get(position).getNombreC());
                intent.putExtra("apaternoC", cuidadorRVModalArrayList.get(position).getApaternoC());
                intent.putExtra("amaternoC", cuidadorRVModalArrayList.get(position).getAmaternoC());
                intent.putExtra("edadC", cuidadorRVModalArrayList.get(position).getEdadC());
                intent.putExtra("telefonoC", cuidadorRVModalArrayList.get(position).getTelefonoC());
                intent.putExtra("parentescoC", cuidadorRVModalArrayList.get(position).getParentescoC());

                //ELIMINAR
                inte = new Intent(context, CuidadorEliminar.class);
                inte.putExtra("nombreC", cuidadorRVModalArrayList.get(position).getNombreC());
                inte.putExtra("apaternoC", cuidadorRVModalArrayList.get(position).getApaternoC());
                inte.putExtra("amaternoC", cuidadorRVModalArrayList.get(position).getAmaternoC());
                inte.putExtra("edadC", cuidadorRVModalArrayList.get(position).getEdadC());
                inte.putExtra("telefonoC", cuidadorRVModalArrayList.get(position).getTelefonoC());
                inte.putExtra("parentescoC", cuidadorRVModalArrayList.get(position).getParentescoC());


                //alerta para editar o eliminar
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());

                // Setting Dialog Title
                alertDialog.setTitle("Opciones: ");

                // Setting Dialog Message
                alertDialog.setMessage("Desea editar o Eliminar el medicamento?");

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
        return cuidadorRVModalArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreCuidador,telefono, parentesco;
        private ImageView cuidadorIV;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nombreCuidador = itemView.findViewById(R.id.nombre_cuid_list);
            telefono = itemView.findViewById(R.id.telefono_list);
            parentesco=itemView.findViewById(R.id.parentesco_list);

        }
    }

    public interface medicamentosClickInterface{
        void onMedicamentosClick(int position);
    }


}