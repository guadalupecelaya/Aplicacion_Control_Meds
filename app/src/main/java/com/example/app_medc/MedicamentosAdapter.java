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
        View views = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentosAdapter.ViewHolder holder, int position) {
        MedicamentosRVModal medicamentoRVModal = medicamentosRVModalArrayList.get(position);
        holder.nombreMedicamento.setText(medicamentoRVModal.getNombreMedicamento());
        holder.farmaceutica.setText(medicamentoRVModal.getFarmaceuticaFabricante());
        holder.mg_meds.setText(medicamentoRVModal.getCantida());
        //Picasso.get().load(medicamentoRVModal.get)

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
