package com.example.actividadlabcorte2.ui.home;

import java.io.Serializable;

public class objvacunacion implements Serializable {

    public String  depa_nombre;
    public String  muni_nombre;
    public String  sede_nombre;
    public String  direccion;
    public String  telefono;
    public String  email;
    public String  naju_nombre;
    public String  fecha_corte_reps;

    public objvacunacion(String depa_nombre, String muni_nombre, String sede_nombre, String direccion, String telefono, String email, String naju_nombre, String fecha_corte_reps) {
        this.depa_nombre = depa_nombre;
        this.muni_nombre = muni_nombre;
        this.sede_nombre = sede_nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.naju_nombre = naju_nombre;
        this.fecha_corte_reps = fecha_corte_reps;
    }

    public String getDepa_nombre() {
        return depa_nombre;
    }

    public String getMuni_nombre() {
        return muni_nombre;
    }

    public String getSede_nombre() {
        return sede_nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getNaju_nombre() {
        return naju_nombre;
    }

    public String getFecha_corte_reps() {
        return fecha_corte_reps;
    }
}
