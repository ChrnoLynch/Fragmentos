package com.example.lprub.contactos.actividades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.lprub.contactos.R;
import com.example.lprub.contactos.adaptadores.Adaptador;
import com.example.lprub.contactos.datos.Agenda;
import com.example.lprub.contactos.datos.Contacto;
import com.example.lprub.contactos.datos.GestionSeguridad;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ActividadPrincipal extends AppCompatActivity implements OnFragmentInteractionListener {

    private OnFragmentInteractionListener listener;

    @Override
    public void onFragmentoInteraccion(Contacto c) {
        FragmentContacto fragmentContacto=(FragmentContacto)getFragmentManager().findFragmentById(R.id.fragment2);
        FragmentLista fragmentLista= (FragmentLista) getFragmentManager().findFragmentById(R.id.fragment1);
        if(fragmentContacto==null||!fragmentContacto.isInLayout()){
            fragmentLista.lanzarContacto(c);
        }else{
            fragmentContacto.update(c);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividadprincipal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }


}
