package com.example.lprub.contactos.datos;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.example.lprub.contactos.datos.GestionContactos.getListaTelefonos;

/**
 * Created by lprub on 15/10/2015.
 */
public class Agenda extends ArrayList implements Parcelable {
    private ArrayList<Contacto> agenda;

    public Agenda (List<Contacto> contactos){
        agenda= (ArrayList<Contacto>) contactos;
    }

    public Agenda (Context contexto){
       List a = GestionContactos.getListaContactos(contexto);
       //ArrayList de objetos Contacto donde se guardaran todos los contactos usados por la aplicación
       agenda = new ArrayList<>();
       Contacto aux;
       for (int i = 0; a.size() > i; i++) {
          aux = (Contacto) a.get(i);
          agenda.add(new Contacto(aux.getId(), aux.getNombre(), getListaTelefonos(contexto, aux.getId())));
       }
    }

    protected Agenda(Parcel in) {
        agenda = in.createTypedArrayList(Contacto.CREATOR);
    }

    public static final Creator<Agenda> CREATOR = new Creator<Agenda>() {
        @Override
        public Agenda createFromParcel(Parcel in) {
            return new Agenda(in);
        }

        @Override
        public Agenda[] newArray(int size) {
            return new Agenda[size];
        }
    };

    public ArrayList<Contacto> getArrayAgenda(){
        return agenda;
    }



    public ArrayList<Contacto> getAgenda(Context contexto) {
        List a = GestionContactos.getListaContactos(contexto);
        //ArrayList de objetos Contacto donde se guardaran todos los contactos usados por la aplicación
        agenda = new ArrayList<>();
        //  Log.v("------------" + b.toString(), "");
        Contacto aux;
        for (int i = 0; a.size() > i; i++) {
            aux = (Contacto) a.get(i);
            agenda.add(new Contacto(aux.getId(), aux.getNombre(), getListaTelefonos(contexto, aux.getId())));
        }
        return agenda;
    }



    public void add(int index, Contacto object) {
        agenda.add(index, object);
    }

    public  Contacto getContacto(int indice){
        return agenda.get(indice);
    }

    public  void setContacto(int indice, Contacto contacto){
        agenda.set(indice, contacto);
    }

    public  void añadirContacto(Contacto contacto){
        agenda.add(contacto);
    }

    public  void ordenarAgenda(){
        Collections.sort(agenda);
     }

    public  void ordenarAgendaInverso(){
        Collections.sort(agenda, new ComparatorInverso());
            }
    @Override
    public Contacto get(int index) {
        return agenda.get(index);
    }

    @Override
    public int size() {
        return agenda.size();
    }


    public ArrayList<Contacto> getAgenda() {
        return agenda;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeArray(agenda.toArray());
    }
}
