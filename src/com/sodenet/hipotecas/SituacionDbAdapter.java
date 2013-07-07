package com.sodenet.hipotecas;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


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

    /**
     * Definimos columnas para lista
     */
    private String[] lista = new String[]{C_COLUMNA_ID, C_COLUMNA_NOMBRE} ;

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

	
    /**
     * Devuelve una lista (_id, nombre) con todos los registros
     */
    public Cursor getLista() throws SQLException
    {
        Cursor c = db.query( true, C_TABLA, lista, null, null, null, null, null, null);

        return c;
    }

}