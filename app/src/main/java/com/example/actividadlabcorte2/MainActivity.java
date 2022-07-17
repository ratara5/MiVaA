package com.example.actividadlabcorte2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.actividadlabcorte2.ui.jobService.servicio_api;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUser;
    private EditText editTextPassword;
    private String user;
    private String password;
    private Button btnIngresar;
    private ProgressBar progres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        btnIngresar = (Button) findViewById(R.id.button);
        progres = (ProgressBar) findViewById(R.id.progressBar);
        ComponentName componentName= new ComponentName(MainActivity.this, servicio_api.class);
        JobInfo info;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            info = new JobInfo.Builder(9782, componentName)
                    .setPersisted(true)
                    .setMinimumLatency(5*60000)
                    .build();
        }else{
            info = new JobInfo.Builder(9782, componentName)
                    .setPersisted(true)
                    .setPeriodic(5*60000)
                    .build();
        }

        JobScheduler jobScheduler=(JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int res= jobScheduler.schedule(info);
        if(res==JobScheduler.RESULT_SUCCESS){
            Log.d("JOB","JOB INICIADO CORRECTAMENTE");
        }else{
            Log.d("JOB","ERROR");
        }
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task1().execute(editTextUser.getText().toString());
                user = editTextUser.getText().toString();
                password = editTextPassword.getText().toString();
            }
        });

    }


    private void login(){

        if ((user.equals("admin"))&&(password.equals("admin"))){

            Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String datetime = dateformat.format(c.getTime());

            startActivity(intent);

            Toast.makeText(this,"Ingreso con exito",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this,"Error, intente nuevamente",Toast.LENGTH_LONG).show();
        }

    }

    private class Task1 extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            progres.setVisibility(View.VISIBLE);
            btnIngresar.setEnabled(false);
        }

        @Override
        protected void onPostExecute(String s) {

            progres.setVisibility(View.INVISIBLE);
            btnIngresar.setEnabled(true);
            login();

        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return strings[0];
        }
    }

}