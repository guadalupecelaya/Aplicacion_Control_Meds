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

public class MedicamentosAdapter extends RecyclerView.Adapter<MedicamentosAdapter.ViewHolder> {
    private ArrayList<MedicamentosRVModal> medicamentosRVModalArrayList;
    private Context context;
    int lastPos = -1;
    //private medicamentosClickInterface MedicamentosClickInterface;




    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nom_meds, farmaceutica, mg;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom_meds = itemView.findViewById(R.id.nombre_med_list);
            farmaceutica=itemView.findViewById(R.id.farmaceutica_list);
            mg=itemView.findViewById(R.id.mg_list);
        }





    }



    public MedicamentosAdapter(ArrayList<MedicamentosRVModal> medicamentosRVModalArrayList, Context context) {
        this.medicamentosRVModalArrayList = medicamentosRVModalArrayList;
        this.context=context;
        //this.MedicamentosClickInterface = medicamentosClickInterface;
    }

    @NonNull
    @Override
    public MedicamentosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(context).inflate(R.layout.list_item_med,parent,false);
        return new ViewHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentosAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MedicamentosRVModal medicamentoRVModal = medicamentosRVModalArrayList.get(position);
        holder.nombreMedicamento.setText(medicamentoRVModal.getNombreMedicamento());
        holder.farmaceutica.setText(medicamentoRVModal.getFarmaceuticaFabricante());
        holder.mg_meds.setText(medicamentoRVModal.getCantida());
        //Picasso.get().load(medicamentoRVModal.get)

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) holder.getAdapterPosition();
                final Intent intent, inte;
                //intent =  new Intent(context, MedicamentoEditar.class);
                intent = new Intent(context, MedicamentoEditar.class);
                inte = new Intent(context, MedicamentoEliminar.class);

                intent.putExtra("nombre", medicamentosRVModalArrayList.get(position).getNombreMedicamento());
                intent.putExtra("viaAdministracion", medicamentosRVModalArrayList.get(position).getViaAdministracion());
                intent.putExtra("cantida", medicamentosRVModalArrayList.get(position).getCantida());
                intent.putExtra("farmaceuticaFabricante", medicamentosRVModalArrayList.get(position).getFarmaceuticaFabricante());
                intent.putExtra("descripcionUso", medicamentosRVModalArrayList.get(position).getDescripcionUso());

                inte.putExtra("nombre", medicamentosRVModalArrayList.get(position).getNombreMedicamento());
                inte.putExtra("viaAdministracion", medicamentosRVModalArrayList.get(position).getViaAdministracion());
                inte.putExtra("cantida", medicamentosRVModalArrayList.get(position).getCantida());
                inte.putExtra("farmaceuticaFabricante", medicamentosRVModalArrayList.get(position).getFarmaceuticaFabricante());
                inte.putExtra("descripcionUso", medicamentosRVModalArrayList.get(position).getDescripcionUso());


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
        return medicamentosRVModalArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreMedicamento,mg_meds, farmaceutica;
        private ImageView medicamentosIV;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nombreMedicamento = itemView.findViewById(R.id.nombre_med_list);
            farmaceutica = itemView.findViewById(R.id.farmaceutica_list);
            mg_meds=itemView.findViewById(R.id.mg_list);

        }
    }

    public interface medicamentosClickInterface{
        void onMedicamentosClick(int position);
    }


}