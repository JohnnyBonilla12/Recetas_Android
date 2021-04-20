package com.example.proyecto_rez;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterIngre extends RecyclerView.Adapter<AdapterIngre.ViewHolder> {

    private List<String> listItems;
    private Context context;
    private String nombre;
    private ProgressDialog dialog;

    public AdapterIngre ( Context context,List<String> listItems,String nombre) {
        this.listItems = listItems;
        this.nombre=nombre;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewingredientes, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Agarrar mi cardview y le pongo el ultimo elemento de la lista de items de ingrediente

        holder.ingrediente.setText(listItems.get(position));



    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView ingrediente;
        public CardView card_view;

        public ViewHolder(View itemView ) {
            super(itemView);


            ingrediente = (TextView) itemView.findViewById(R.id.ingrediente);
            card_view = (CardView) itemView.findViewById(R.id.card_view);

        }

}




}