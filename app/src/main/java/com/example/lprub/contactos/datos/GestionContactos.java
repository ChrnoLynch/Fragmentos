package com.example.lprub.contactos.datos;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.example.lprub.contactos.datos.Contacto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lprub on 11/10/2015.
 */
public class GestionContactos {

    /*private List contactos;
    private List numeros;

    public GestionContactos(Context contexto) {
        this.contactos = getListaContactos(contexto);
    }

    public List getContactos() {
        return contactos;
    }*/

    public static List<Contacto> getListaContactos(Context contexto){
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String proyeccion[] = null;
        String seleccion = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = ? and " +
                ContactsContract.Contacts.HAS_PHONE_NUMBER + "= ?";
        String argumentos[] = new String[]{"1","1"};
        String orden = ContactsContract.Contacts.DISPLAY_NAME + " collate localized asc";
        Cursor cursor = contexto.getContentResolver().query(uri, proyeccion, seleccion, argumentos, orden);
        int indiceId = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int indiceNombre = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        List<Contacto> lista = new ArrayList<>();
        Contacto contacto;
        while(cursor.moveToNext()){
            contacto = new Contacto();
            contacto.setId((int)cursor.getLong(indiceId));
            contacto.setNombre(cursor.getString(indiceNombre));
            lista.add(contacto);
        }
        return lista;
    }

    public static List<String> getListaTelefonos(Context contexto, long id){
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String proyeccion[] = null;
        String seleccion = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";
        String argumentos[] = new String[]{id+""};
        String orden = ContactsContract.CommonDataKinds.Phone.NUMBER;
        Cursor cursor = contexto.getContentResolver().query(uri, proyeccion, seleccion, argumentos, orden);
        int indiceNumero = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        List<String> lista = new ArrayList<>();
        String numero;
        while(cursor.moveToNext()){
            numero = cursor.getString(indiceNumero);
            lista.add(numero);
        }
        return lista;
    }

}