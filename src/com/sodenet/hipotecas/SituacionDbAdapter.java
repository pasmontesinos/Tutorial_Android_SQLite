package com.sodenet.hipotecas;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class SituacionDbAdapter {

	/**
	 * Definimos constante con el nombre de la tabla
	 */
	public static final String C_TABLA = "SITUACION" ;

    /**
     * Definimos constantes con el nombre de las columnas de la tabla
     */
    public static final String C_COLUMNA_ID	= "_id";
    public static final String C_COLUMNA_NOMBRE = "sit_nombre";

	private Context contexto;
	private HipotecaDbHelper dbHelper;
	private SQLiteDatabase db;

    /*
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{
            C_COLUMNA_ID,
            C_COLUMNA_NOMBRE
    } ;

	public SituacionDbAdapter(Context context)
	{
		this.contexto = context;
	}

	public SituacionDbAdapter abrir() throws SQLException
	{
		dbHelper = new HipotecaDbHelper(contexto);
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void cerrar()
	{
		dbHelper.close();
	}


    /*
     * Devuelve cursor con todos las columnas del registro
     */
    public Cursor getRegistro(long id) throws SQLException
    {
        if (db == null)
            abrir();

        Cursor c = db.query( true, C_TABLA, columnas, C_COLUMNA_ID + "=" + id, null, null, null, null, null);

        //Nos movemos al primer registro de la consulta
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public ArrayList<Situacion> getSituaciones(String filtro)
    {
        ArrayList<Situacion> situaciones = new ArrayList<Situacion>();

        if (db == null)
            abrir();

        Cursor c = db.query(true, C_TABLA, columnas, filtro, null, null, null, null, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            situaciones.add(Situacion.cursorToSituacion(contexto, c));
        }

        c.close();

        return situaciones;
    }
}