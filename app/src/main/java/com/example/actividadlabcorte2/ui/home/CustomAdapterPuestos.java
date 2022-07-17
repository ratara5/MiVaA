package com.example.actividadlabcorte2.ui.home;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actividadlabcorte2.R;

import java.util.List;

public class CustomAdapterPuestos extends RecyclerView.Adapter<CustomAdapterPuestos.ViewHolder> {
    List<objvacunacion> List;
    public seleccion seleccion;
    Context context;
    public CustomAdapterPuestos(List<objvacunacion> list, Context context, seleccion seleccion){
        List = list;
        this.context= context;
        this.seleccion=seleccion;
    }


    @NonNull
    @Override
    public CustomAdapterPuestos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.puestos,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterPuestos.ViewHolder holder, int position) {
        objvacunacion Objvacunacion = List.get(position);
        holder.nombre.setText(List.get(position).getMuni_nombre());
        holder.sedenombre.setText(List.get(position).getSede_nombre());
        holder.direccion.setText(List.get(position).getDireccion());
        holder.telefono.setText(List.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public interface seleccion{
        void seleccion(objvacunacion objvacunacion);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombre;
        public TextView  sedenombre;
        public TextView  direccion;
        public TextView  telefono;
        public Button guardar;

        public ViewHolder(View view) {
            super(view);
            nombre = (TextView) view.findViewById(R.id.nombretv);
            sedenombre = (TextView) view.findViewById(R.id.sedetv);
            direccion = (TextView) view.findViewById(R.id.direcciontv);
            telefono = (TextView) view.findViewById(R.id.telefonotv);
            guardar  = (Button) view.findViewById(R.id.guardar);
            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    seleccion.seleccion(List.get(getAdapterPosition()));
                }
            });
                }
    }
}
