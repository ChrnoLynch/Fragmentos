package com.example.lprub.contactos.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.lprub.contactos.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lprub on 14/10/2015.
 */


//Este adaptador nos sirve para poner los numeros extras de un telefono y rellenar el segundo ListView
public class AdaptadorNumeros extends ArrayAdapter<String> {
    private ArrayList<String> numeros;
    private int recurso;
    private Context contexto;

    public AdaptadorNumeros(Context contexto, int recurso, ArrayList<String> numeros) {
        super(contexto, recurso, numeros);
        this.contexto=contexto;
        this.recurso=recurso;
        this.numeros=numeros;
    }

    static class SalvadorReferencia{
        public TextView tvSubNumero;
    }

    @Override
    public View getView(int posicion, View v, ViewGroup padre) {
        LayoutInflater i = (LayoutInflater) contexto.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        SalvadorReferencia sr = null;
        if (v == null) {
            v = i.inflate(recurso, null);
            sr = new SalvadorReferencia();
            sr.tvSubNumero=(TextView) v.findViewById(R.id.tvSubNumero);
            v.setTag(sr);
        } else {
            sr = (SalvadorReferencia) v.getTag();
        }
        //Ponemos cada numero en el TextView que tenemos en el layout numeros.
            sr.tvSubNumero.setText(numeros.get(posicion));
            return v;
    }
}
