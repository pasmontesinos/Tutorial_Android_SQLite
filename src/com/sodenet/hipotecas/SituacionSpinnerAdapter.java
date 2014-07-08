package com.sodenet.hipotecas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SituacionSpinnerAdapter extends ArrayAdapter<Situacion>
{
    private static class ViewHolder {
        TextView text1;
    }

    public SituacionSpinnerAdapter(Context context, ArrayList<Situacion> situaciones) {
        super(context, android.R.layout.simple_spinner_dropdown_item, situaciones);
    }

    @Override
    public long getItemId(int position)
    {
        return getItem(position).getId();
    }

    public int getPositionById(long id) {

        if (!this.isEmpty()) {
            for (int i = 0; i < this.getCount(); i++) {
                if (this.getItemId(i) == id) {
                    return i ;
                }
            }
        }

        return 0 ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Situacion situacion = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
            viewHolder.text1 = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text1.setText(situacion.getNombre());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        Situacion situacion = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            viewHolder.text1 = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text1.setText(situacion.getNombre());

        return convertView;
    }

}
