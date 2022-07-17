package com.example.actividadlabcorte2.ui.jobService;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.actividadlabcorte2.Interface.JsonPlaceHolderAPI;
import com.example.actividadlabcorte2.R;
import com.example.actividadlabcorte2.ui.home.objvacunacion;
import com.example.actividadlabcorte2.ui.sqlhelper;
import com.example.actividadlabcorte2.ui.utilidades.utilidades;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class servicio_api extends JobService {
    List<objvacunacion> objvacunacionList = new ArrayList<>();
    boolean cancelarServicio = false;
    boolean monteria;
    sqlhelper conn = new sqlhelper(this, utilidades.TABLA_PUESTOS, null, 1);
    private final String LINK = "https://www.datos.gov.co/resource/";

    @Override
    public boolean onStartJob(JobParameters params) {
        job(params);
        return true;
    }

    public boolean onStopJob(JobParameters params) {
        cancelarServicio = true;
        return false;
    }

    private void job(final JobParameters params) {
        new Thread(() -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(LINK)
                    .build();

            JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
            Call<List<objvacunacion>> call = jsonPlaceHolderAPI.getPost();
            call.enqueue(new Callback<List<objvacunacion>>() {
                @Override
                public void onResponse(Call<List<objvacunacion>> call, Response<List<objvacunacion>> response) {
                    if (!response.isSuccessful()) {
                        return;
                    }
                    List<objvacunacion> postObjvacunacion = response.body();
                    for (objvacunacion post : postObjvacunacion) {
                        objvacunacionList.add(new objvacunacion(post.getDepa_nombre(), post.getMuni_nombre(), post.getSede_nombre(), post.getDireccion(), post.getTelefono(), post.getEmail(), post.getNaju_nombre()
                                , post.getFecha_corte_reps()));
                    }
                    new Thread(() -> {
                        for (int i = 0; i <= objvacunacionList.size() - 1; i++) {
                            if (objvacunacionList.get(i).getMuni_nombre().equalsIgnoreCase("MONTERIA")) {
                                monteria = true;
                                sqli(i);
                            } else {
                                monteria = false;
                            }
                        }
                        notificacion();
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                    }).start();
                }

                @Override
                public void onFailure(Call<List<objvacunacion>> call, Throwable t) {
                    System.out.println(t);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }).start();
        jobFinished(params, true);
    }

    public void sqli(int i) {
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utilidades.CAMPO_DEPARTAMENTO, (objvacunacionList.get(i).getDepa_nombre()));
        values.put(utilidades.CAMPO_MUNI, (objvacunacionList.get(i).getMuni_nombre()));
        values.put(utilidades.CAMPO_SEDE, (objvacunacionList.get(i).getSede_nombre()));
        values.put(utilidades.CAMPO_DIR, (objvacunacionList.get(i).getDireccion()));
        values.put(utilidades.CAMPO_TEL, (objvacunacionList.get(i).getTelefono()));
        values.put(utilidades.CAMPO_EMAIL, (objvacunacionList.get(i).getEmail()));
        values.put(utilidades.CAMPO_naju_nombre, (objvacunacionList.get(i).getNaju_nombre()));
        values.put(utilidades.CAMPO_fecha_corte_reps, (objvacunacionList.get(i).getFecha_corte_reps()));
        db.insert(utilidades.TABLA_PUESTOS, "id", values);
    }

    public void notificacion() {
        String title, content;
        if (monteria) {
            title="PUESTO DE VACUNACION ENCONTRADO";
            content="Notificación automatica";
        } else {
            title="PUESTO DE VACUNACION NO ENCONTRADO";
            content="Notificación automatica";
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "puestos";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("idnoti", name, importance);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "idnoti")
                .setSmallIcon(R.drawable.ic_banner_virus)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_banner_virus))
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(111, builder.build());
    }
}