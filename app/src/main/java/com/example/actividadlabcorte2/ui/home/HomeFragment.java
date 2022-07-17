package com.example.actividadlabcorte2.ui.home;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actividadlabcorte2.Interface.JsonPlaceHolderAPI;
import com.example.actividadlabcorte2.R;
import com.example.actividadlabcorte2.ui.sql.datos;
import com.example.actividadlabcorte2.ui.sqlhelper;
import com.example.actividadlabcorte2.ui.utilidades.utilidades;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment  implements CustomAdapterPuestos.seleccion{

    private HomeViewModel homeViewModel;
    public List<objvacunacion> objvacunacionList;
    private RecyclerView recyclerViewPosts;
    private CustomAdapterPuestos customAdapterPuestos;
    View root;
    sqlhelper conn;
    private final String LINK = "https://www.datos.gov.co/resource/";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewPosts = (RecyclerView) root.findViewById(R.id.RecyclerViewPosts);
        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(root.getContext()));
        conn = new sqlhelper(root.getContext(),"bd_puestos",null,1);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(LINK)
                        .build();

                JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

                Call<List<objvacunacion>> call = jsonPlaceHolderAPI.getPost();

                call.enqueue(new Callback<List<objvacunacion>>() {
                    @Override
                    public void onResponse(Call<List<objvacunacion>> call, Response<List<objvacunacion>> response) {
                        System.out.println(response);
                        if(!response.isSuccessful()){
                            Toast.makeText(root.getContext(),"Codigo: " + response.code(),Toast.LENGTH_SHORT).show();
                            return;
                        }

                        List<objvacunacion> objvacunacionList2 = response.body();
                        String content = "";
                        customAdapterPuestos = new CustomAdapterPuestos(objvacunacionList2,root.getContext(),HomeFragment.this::seleccion);
                        recyclerViewPosts.setAdapter(customAdapterPuestos);

                    }

                    @Override
                    public void onFailure(Call<List<objvacunacion>> call, Throwable t) {
                        System.out.println(t);
                    }
                });
            }
        });
        return root;

    }
    @Override
    public void seleccion(objvacunacion objvacunacion) {
        SQLiteDatabase db =conn.getWritableDatabase();
        SQLiteDatabase read=conn.getReadableDatabase();
        Cursor cursor=read.rawQuery("SELECT * FROM "+utilidades.TABLA_PUESTOS+" WHERE "+utilidades.CAMPO_SEDE+" = "+ "'"+objvacunacion.sede_nombre+"'",null);
        if(cursor.getCount()==0){
            ContentValues values = new ContentValues();
            values.put(utilidades.CAMPO_DEPARTAMENTO,objvacunacion.depa_nombre);
            values.put(utilidades.CAMPO_MUNI,objvacunacion.muni_nombre);
            values.put(utilidades.CAMPO_SEDE,objvacunacion.sede_nombre);
            values.put(utilidades.CAMPO_DIR,objvacunacion.direccion);
            values.put(utilidades.CAMPO_TEL,objvacunacion.telefono);
            values.put(utilidades.CAMPO_EMAIL,objvacunacion.email);
            values.put(utilidades.CAMPO_naju_nombre,objvacunacion.naju_nombre);
            values.put(utilidades.CAMPO_fecha_corte_reps,objvacunacion.fecha_corte_reps);
            Long idres=db.insert(utilidades.TABLA_PUESTOS,utilidades.CAMPO_ID,values);
            System.out.println("id registro: "+idres);
            Toast.makeText(root.getContext(),"Datos guardados satisfactoriamente",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(root.getContext(),"Error, el objeto fue guardado anteriormente",Toast.LENGTH_SHORT).show();
        }

    }

}