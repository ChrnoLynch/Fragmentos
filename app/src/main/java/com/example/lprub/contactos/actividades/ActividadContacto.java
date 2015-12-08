package com.example.lprub.contactos.actividades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lprub.contactos.R;
import com.example.lprub.contactos.datos.Contacto;

public class ActividadContacto extends AppCompatActivity {
    private Contacto save,cont;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i=this.getIntent();
        if(savedInstanceState!=null){
            cont=savedInstanceState.getParcelable("contacto");
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.actividadcontacto);
            Bundle bundle = getIntent().getExtras();
            cont = bundle.getParcelable("Contacto");
            FragmentContacto fragmento = (FragmentContacto) getFragmentManager().findFragmentById(R.id.fragment2);
            fragmento.update(cont);
            System.out.println("VERDADERO"+cont);
        }else{
//            Intent regreso=new Intent(this, ActividadPrincipal.class);
//            Bundle bundle=new Bundle();
//            bundle.putParcelable("Contacto", save);
//            regreso.putExtras(bundle);
//            startActivityForResult(regreso,);
            Bundle b=new Bundle();
            b.putParcelable("contacto",cont);
            i.putExtras(b);
            setResult(Activity.RESULT_OK, i);
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("contacto",cont);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cont=savedInstanceState.getParcelable("contacto");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_contacto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
