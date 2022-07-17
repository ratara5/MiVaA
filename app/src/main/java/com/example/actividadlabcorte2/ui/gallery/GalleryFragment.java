package com.example.actividadlabcorte2.ui.gallery;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actividadlabcorte2.R;
import com.example.actividadlabcorte2.ui.sql.datos;
import com.example.actividadlabcorte2.ui.sqlhelper;
import com.example.actividadlabcorte2.ui.utilidades.utilidades;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    ArrayList<datos> datosArrayList;
    RecyclerView recyclerView;
    sqlhelper conn;
    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        conn = new sqlhelper(root.getContext(),"bd_puestos",null,1);
        datosArrayList=new ArrayList<>();
        recyclerView= root.findViewById(R.id.recyclersql);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        consultarpuestos();
        adaptadorsql adaptadorsql=new adaptadorsql(datosArrayList);
        recyclerView.setAdapter(adaptadorsql);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    private void consultarpuestos() {
        SQLiteDatabase db=conn.getReadableDatabase();
        datos datos=null;
        Cursor cursor=db.rawQuery("SELECT * FROM " + utilidades.TABLA_PUESTOS,null);
        while (cursor.moveToNext()){
            datos = new datos();
            datos.setDepa_nombre(cursor.getString(0));
            datos.setMuni_nombre(cursor.getString(1));
            datos.setSede_nombre(cursor.getString(2));
            datos.setDireccion(cursor.getString(3));
            datos.setTelefono(cursor.getString(4));
            datos.setEmail(cursor.getString(5));
            datos.setNaju_nombre(cursor.getString(6));
            datos.setFecha_corte_reps(cursor.getString(7));

            datosArrayList.add(datos);
        }
    }
}