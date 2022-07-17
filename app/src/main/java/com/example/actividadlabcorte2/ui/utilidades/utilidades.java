package com.example.actividadlabcorte2.ui.utilidades;

public class utilidades {
    public static final String TABLA_PUESTOS="bd_puestos";
    public static final String CAMPO_ID="";
    public static final String CAMPO_DEPARTAMENTO="departamento";
    public static final String CAMPO_MUNI="municipio";
    public static final String CAMPO_SEDE="sede";
    public static final String CAMPO_DIR="direccion";
    public static final String CAMPO_TEL="telefono";
    public static final String CAMPO_EMAIL="email";
    public static final String CAMPO_naju_nombre="naju_nombre";
    public static final String CAMPO_fecha_corte_reps="fecha_corte_reps";

    public static final String creardb="CREATE TABLE " +TABLA_PUESTOS +" ("+CAMPO_DEPARTAMENTO+" TEXT,"
            +CAMPO_MUNI+" TEXT, "+CAMPO_SEDE+" TEXT, "+CAMPO_DIR+" TEXT, "+CAMPO_TEL+" TEXT, " +
            CAMPO_EMAIL+" TEXT, "+CAMPO_naju_nombre+" TEXT, "+CAMPO_fecha_corte_reps+" TEXT)";
}
