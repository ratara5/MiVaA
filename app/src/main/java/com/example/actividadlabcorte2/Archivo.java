package com.example.actividadlabcorte2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class Archivo {

    private List<String> horas = new ArrayList<>();

    public Archivo() {
    }



    public Archivo(List<String> horas) {
        this.horas = horas;
    }

    public int Tam(){
        return horas.size();
    }

    public String getHoras(int pos) {
        return horas.get(pos);
    }
}
