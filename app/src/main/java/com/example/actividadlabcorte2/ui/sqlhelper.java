package com.example.actividadlabcorte2.ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.actividadlabcorte2.ui.utilidades.utilidades;

public class sqlhelper extends SQLiteOpenHelper {

    public sqlhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(utilidades.creardb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+utilidades.TABLA_PUESTOS);
        db.execSQL(utilidades.creardb);
    }
}
