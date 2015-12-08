package com.example.lprub.contactos.datos;

import com.example.lprub.contactos.datos.Contacto;

import java.util.Comparator;

/**
 * Created by Chrno on 19/10/2015.
 */
public class ComparatorInverso implements Comparator<Contacto>{
    @Override
    public  int compare(Contacto contacto1, Contacto contacto2){
        return contacto2.getNombre().compareToIgnoreCase(contacto1.getNombre());
    }
}
