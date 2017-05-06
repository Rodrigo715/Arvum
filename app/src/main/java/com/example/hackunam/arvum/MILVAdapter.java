package com.example.hackunam.arvum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by rocko on 06/05/2017.
 */

public class MILVAdapter extends BaseAdapter {

    ArrayList<Planta> plantas;
    Context context;
    String comollamas, especie;

    public MILVAdapter(ArrayList<Planta> plantas, Context context){
        this.plantas=plantas;
        this.context=context;
        this.comollamas=comollamas;
        this.especie=especie;
    }

    @Override
    public int getCount() {
        return plantas.size();
    }

    @Override
    public Object getItem(int position) {
        return plantas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView= LayoutInflater.from(context)
                    .inflate(R.layout.planta_layout, parent, false);
        }

        Planta pl= plantas.get(position);

        TextView nombre = (TextView) convertView.findViewById(R.id.tv_nombrePlanta_main);
        TextView descripcion= (TextView) convertView.findViewById(R.id.tv_descrPlanta_main);

        nombre.setText(pl.getName());
        descripcion.setText(pl.getDescription());

        return convertView;
    }
}
