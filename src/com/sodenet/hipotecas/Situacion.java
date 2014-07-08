package com.sodenet.hipotecas;


import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class Situacion {

    private Context context;

    private Long id;
    private String nombre;

    public Situacion(Context context)
    {
        this.context = context;
    }

    public Situacion(Context context, Long id, String nombre) {
        this.context = context;
        this.id = id;
        this.nombre = nombre;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static Situacion find(Context context, long id)
    {
        SituacionDbAdapter dbAdapter = new SituacionDbAdapter(context);

        Cursor c = dbAdapter.getRegistro(id);

        Situacion situacion = Situacion.cursorToSituacion(context, c);

        c.close();

        return situacion;
    }

    public static ArrayList<Situacion> getAll(Context context, String filter)
    {
        SituacionDbAdapter dbAdapter = new SituacionDbAdapter(context);

        return dbAdapter.getSituaciones(filter) ;

    }


    public static Situacion cursorToSituacion(Context context, Cursor c)
    {
        Situacion situacion = null;

        if (c != null)
        {
            situacion = new Situacion(context);

            situacion.setId(c.getLong(c.getColumnIndex(SituacionDbAdapter.C_COLUMNA_ID)));
            situacion.setNombre(c.getString(c.getColumnIndex(SituacionDbAdapter.C_COLUMNA_NOMBRE)));
        }

        return situacion ;
    }

}
