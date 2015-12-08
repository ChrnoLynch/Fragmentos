package com.example.lprub.contactos.actividades;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lprub.contactos.R;
import com.example.lprub.contactos.datos.Contacto;

import java.io.File;


public class FragmentContacto extends Fragment {


    private OnFragmentInteractionListener listener;
    private TextView tvNombre,tvNumeros;
    private ImageView foto;
    private File imgFile;

    public FragmentContacto() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_actividad_contacto, container, false);
        tvNombre= (TextView) v.findViewById(R.id.tvNombre);
        tvNumeros= (TextView) v.findViewById(R.id.tvNumeros);
        foto= (ImageView) v.findViewById(R.id.ivfoto);
        System.out.println("Esto dos veces");
        return v;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void update(Contacto c) {
        tvNombre.setText(c.getNombre());
        tvNumeros.setText(c.getNumerosCadena());
        if (c.getFoto()!=null) {
            imgFile = new File(c.getFoto());
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                foto.setImageBitmap(myBitmap);
            }
        }
        else{
            foto.setImageResource(R.mipmap.ic_launcher);
        }
    }


}
