package com.sodenet.hipotecas;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class Hipoteca {

    private Context context;

    private Long id;
    private String nombre;
    private String condiciones;
    private String contacto;
    private String email;
    private String telefono;
    private String observaciones;
    private boolean pasivo;
    private Long situacionId;

    public Hipoteca(Context context)
    {
        this.context = context;
    }

    public Hipoteca(Context context, Long id, String nombre, String condiciones, String contacto, String email, String telefono, String observaciones, boolean pasivo, Long situacionId) {
        this.context = context;
        this.id = id;
        this.nombre = nombre;
        this.condiciones = condiciones;
        this.contacto = contacto;
        this.email = email;
        this.telefono = telefono;
        this.observaciones = observaciones;
        this.pasivo = pasivo;
        this.situacionId = situacionId;
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

    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isPasivo() {
        return pasivo;
    }

    public void setPasivo(boolean pasivo) {
        this.pasivo = pasivo;
    }

    public Long getSituacionId() {
        return situacionId;
    }

    public void setSituacionId(Long situacionId) {
        this.situacionId = situacionId;
    }

    public static Hipoteca find(Context context, long id)
    {
        HipotecaDbAdapter dbAdapter = new HipotecaDbAdapter(context);

        Cursor c = dbAdapter.getRegistro(id);

        Hipoteca hipoteca = Hipoteca.cursorToHipoteca(context, c);

        c.close();

        return hipoteca;
    }

    public static Hipoteca cursorToHipoteca(Context context, Cursor c)
    {
        Hipoteca hipoteca = null;

        if (c != null)
        {
            hipoteca = new Hipoteca(context);

            hipoteca.setId(c.getLong(c.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_ID)));
            hipoteca.setNombre(c.getString(c.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_NOMBRE)));
            hipoteca.setCondiciones(c.getString(c.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_CONDICIONES)));
            hipoteca.setContacto(c.getString(c.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_CONTACTO)));
            hipoteca.setEmail(c.getString(c.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_EMAIL)));
            hipoteca.setTelefono(c.getString(c.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_TELEFONO)));
            hipoteca.setObservaciones(c.getString(c.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_OBSERVACIONES)));
            hipoteca.setPasivo(c.getString(c.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_PASIVO)).equals("S"));
            hipoteca.setSituacionId(c.getLong(c.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_SITUACION)));
        }

        return hipoteca ;
    }

    private ContentValues toContentValues()
    {
        ContentValues reg = new ContentValues();

        reg.put(HipotecaDbAdapter.C_COLUMNA_ID, this.getId());
        reg.put(HipotecaDbAdapter.C_COLUMNA_NOMBRE, this.getNombre());
        reg.put(HipotecaDbAdapter.C_COLUMNA_CONDICIONES, this.getCondiciones());
        reg.put(HipotecaDbAdapter.C_COLUMNA_CONTACTO, this.getContacto());
        reg.put(HipotecaDbAdapter.C_COLUMNA_EMAIL, this.getEmail());
        reg.put(HipotecaDbAdapter.C_COLUMNA_TELEFONO, this.getTelefono());
        reg.put(HipotecaDbAdapter.C_COLUMNA_OBSERVACIONES, this.getObservaciones());
        reg.put(HipotecaDbAdapter.C_COLUMNA_PASIVO, (this.isPasivo())?"S":"N");
        reg.put(HipotecaDbAdapter.C_COLUMNA_SITUACION, this.getSituacionId());

        return reg;
    }

    public long save()
    {
        HipotecaDbAdapter dbAdapter = new HipotecaDbAdapter(this.getContext());

        // comprobamos si estamos insertando o actualizando según esté o no relleno el identificador
        if ((this.getId() == null) || (!dbAdapter.exists(this.getId())))
        {
            long nuevoId = dbAdapter.insert(this.toContentValues());

            if (nuevoId != -1)
            {
                this.setId(nuevoId);
            }
        }
        else
        {
            dbAdapter.update(this.toContentValues());
        }

        return this.getId();
    }

    public long delete()
    {
        // borramos el registro
        HipotecaDbAdapter dbAdapter = new HipotecaDbAdapter(this.getContext());

        return dbAdapter.delete(this.getId());
    }
}
