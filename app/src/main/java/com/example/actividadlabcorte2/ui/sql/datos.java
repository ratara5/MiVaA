package com.example.actividadlabcorte2.ui.sql;

public class datos {

    public String  depa_nombre;
    public String  muni_nombre;
    public String  sede_nombre;
    public String  direccion;
    public String  telefono;
    public String  email;
    public String  naju_nombre;
    public String  fecha_corte_reps;

    public datos() {
        depa_nombre = "";
        muni_nombre = "";
        sede_nombre = "";
        direccion = "";
        telefono ="";
        email = "";
        naju_nombre = "";
        fecha_corte_reps = "";
    }

    public void setDepa_nombre(String depa_nombre) {
        this.depa_nombre = depa_nombre;
    }


    public void setMuni_nombre(String muni_nombre) {
        this.muni_nombre = muni_nombre;
    }

    public void setSede_nombre(String sede_nombre) {
        this.sede_nombre = sede_nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNaju_nombre(String naju_nombre) {
        this.naju_nombre = naju_nombre;
    }

    public void setFecha_corte_reps(String fecha_corte_reps) {
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

