package com.sodenet.hipotecas;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.graphics.Color;

public class HipotecaCursorAdapter extends CursorAdapter
{
	
	private HipotecaDbAdapter dbAdapter = null ;
	
	public HipotecaCursorAdapter(Context context, Cursor c)
	{
		super(context, c);
		dbAdapter = new HipotecaDbAdapter(context);
		dbAdapter.abrir();
	}

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        TextView tv = (TextView) view ;

        tv.setText(cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_NOMBRE)));

        if (cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_PASIVO)).equals("S"))
        {
            tv.setTextColor(Color.GRAY);
        }
        else
        {
            tv.setTextColor(Color.BLACK);
        }
    }

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent)
	{
		final LayoutInflater inflater = LayoutInflater.from(context);
		final View view = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
		
		return view;
	}
}
