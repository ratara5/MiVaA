package com.example.actividadlabcorte2.ui.jobService;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.actividadlabcorte2.R;

public class servicio extends Fragment {

    private servicioViewModel servicioViewModel;
    private Button parar, iniciar;
    View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        servicioViewModel = new ViewModelProvider(this).get(servicioViewModel.class);
        root = inflater.inflate(R.layout.fragment_servicio, container, false);
        parar = (Button) root.findViewById(R.id.desactivar);
        iniciar = (Button) root.findViewById(R.id.activar);
        servicioViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                iniciar.setEnabled(false);
                parar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JobScheduler scheduler = (JobScheduler) getActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE);
                        scheduler.cancel(9782);
                        Toast.makeText(root.getContext(), "Servicio cancelado con exito", Toast.LENGTH_LONG).show();
                        parar.setEnabled(false);
                        iniciar.setEnabled(true);
                    }
                });
                iniciar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ComponentName componentName = new ComponentName(root.getContext(), servicio_api.class);
                        JobInfo info;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            info = new JobInfo.Builder(9782, componentName)
                                    .setPersisted(true)
                                    .setMinimumLatency(5 * 60000)
                                    .build();
                        } else {
                            info = new JobInfo.Builder(9782, componentName)
                                    .setPersisted(true)
                                    .setPeriodic(5 * 60000)
                                    .build();
                        }
                        JobScheduler jobScheduler = (JobScheduler) getActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE);
                        int res = jobScheduler.schedule(info);
                        if (res == JobScheduler.RESULT_SUCCESS) {
                            Toast.makeText(root.getContext(), "Servicio iniciado con exito", Toast.LENGTH_LONG).show();
                            parar.setEnabled(true);
                            iniciar.setEnabled(false);
                        } else {
                            Toast.makeText(root.getContext(), "Error al iniciar el servicio", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        return root;
    }
}
