package com.example.lprub.contactos.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lprub.contactos.R;
import com.example.lprub.contactos.datos.Agenda;
import com.example.lprub.contactos.datos.Contacto;

import java.util.ArrayList;

/**
 * Created by lprub on 11/10/2015.
 */
public class Adaptador extends ArrayAdapter<Contacto> {
    private static LayoutInflater inflater = null;
    private Context contexto;
    private int recurso;
    private Agenda agenda;

    private AdaptadorNumeros adaptadorNumeros;

    static class SalvadorReferencia {
        public TextView tvNombre, tvNumero;
        public ImageView ivPlus;
        public ListView lvPlus;
    }

    public Adaptador(Context contexto, int recurso, Agenda agenda) {
        super(contexto, recurso, agenda);
        this.contexto = contexto;
        this.recurso = recurso;
        this.agenda = agenda;
    }

    @Override
    public View getView(final int posicion, View vista, ViewGroup padre) {
        LayoutInflater i = (LayoutInflater) contexto.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        SalvadorReferencia sr = new SalvadorReferencia();
        if (vista == null) {
            vista = i.inflate(recurso, null);
            sr.tvNombre = (TextView) vista.findViewById(R.id.tvNombre);
            sr.tvNumero = (TextView) vista.findViewById(R.id.tvNumero);
            sr.ivPlus = (ImageView) vista.findViewById(R.id.ivPlus);
            sr.lvPlus = (ListView) vista.findViewById(R.id.lvPlus);
            vista.setTag(sr);
        } else {
            sr = (SalvadorReferencia) vista.getTag();
        }
        vista.setId(posicion);
        //Metemos el nombre y el primer numero de nuestro ArrayList en los textView
        sr.tvNombre.setText(agenda.get(posicion).getNombre());
        sr.tvNumero.setText(agenda.get(posicion).getNumero(0));
        if (agenda.get(posicion).getNumeros().size() > 1) {
            //Copiamos nuestro array de numeros en un array auxiliar para poder borrar la posicion 0
            // que es el primer numero que ya hemos mostrado junto al contacto, y por tanto, no
            // aparezca en nuestro expandible.
            ArrayList aux=new ArrayList((ArrayList)agenda.get(posicion).getNumeros());
            aux.remove(0);
            //Creamos un objeto de nuestro AdaptadorNumeros al que le pasamos nuestro array de todos
            //los numeros excepto el primero, para que nos rellene nuestro segundo ListView
            adaptadorNumeros = new AdaptadorNumeros(contexto, R.layout.numeros, aux);
            sr.lvPlus.setAdapter(adaptadorNumeros);
            sr.lvPlus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                    String fono = agenda.get(posicion).getNumero((pos + 1));
                    Uri numero = Uri.parse("tel:" + fono.toString());
                    Intent intent = new Intent(Intent.ACTION_CALL, numero);
                    contexto.startActivity(intent);
                }
            });

            //El metodo onClick que le hemos colocado a la imagen nos hara visible o gone el segundo
            //listview que tenemos en nuestro item.
            final SalvadorReferencia finalSr = sr;
            sr.ivPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Buscamos en el view principal, el view de la posición en la que se encuentre
                    //la imagen que hemos pulsado, y asi distinguir con que item estamos trabajando.
                    View las = v.getRootView().findViewById(posicion);
                    finalSr.lvPlus = (ListView) las.findViewById(R.id.lvPlus);
                    finalSr.ivPlus = (ImageView) las.findViewById(R.id.ivPlus);
                    if (finalSr.lvPlus.getVisibility() == View.GONE) {
                        finalSr.lvPlus.setVisibility(View.VISIBLE);
                        //Hacemos que nuestro ImageView se active o desactive para que asi se cambie
                        //nuestra imagen al pulsarlo gracias al boton.xml que hemos creado en Drawable.
                        finalSr.ivPlus.setActivated(true);
                    } else if (finalSr.lvPlus.getVisibility() == View.VISIBLE){
                        finalSr.lvPlus.setVisibility(View.GONE);
                        finalSr.ivPlus.setActivated(false);

                    }
                    Adaptador.this.notifyDataSetChanged();
                    actualizarSubLista();

                }
            });
        }
            //Nuestro boton para sacar los numeros extras solo estara disponible si tenemos más de un numero.
            botonSubInfo(sr,posicion);
            return vista;
        }


    //Este metodo nos sirve para informar de cuando hemos añadido un numero extra a un contacto, este
    //se actualize en el segundo ListView
    public void actualizarSubLista(){
        adaptadorNumeros.notifyDataSetChanged();
    }

    //Metodo para mostrar o no el boton que nos abre el subpanel con más informacion de los contactos
    public void botonSubInfo(SalvadorReferencia sr,int posicion) {
        if (agenda.getContacto(posicion).sizeNumeros() > 1) {
            sr.ivPlus.setVisibility(View.VISIBLE);
        } else {
            sr.ivPlus.setVisibility(View.INVISIBLE);
        }
    }
}


