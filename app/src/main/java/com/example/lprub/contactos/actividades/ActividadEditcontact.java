package com.example.lprub.contactos.actividades;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lprub.contactos.R;
import com.example.lprub.contactos.datos.Agenda;
import com.example.lprub.contactos.datos.Contacto;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by lprub on 18/10/2015.
 */
public class ActividadEditcontact extends AppCompatActivity{
    private EditText etNombre, etNumero1, etNumero2, etNumero3, etNumero4;
    private long id;
    private Contacto aux;
    private String rutaFoto=null;
    private ImageView foto;
    private File imgFile;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulariocontac);
        etNombre=(EditText)this.findViewById(R.id.etNombre);
        etNumero1=(EditText)this.findViewById(R.id.etNumero1);
        etNumero2=(EditText)this.findViewById(R.id.etNumero2);
        etNumero3=(EditText)this.findViewById(R.id.etNumero3);
        etNumero4=(EditText)this.findViewById(R.id.etNumero4);
        etNumero2.setHint(this.getText(R.string.alternativo));
        etNumero3.setHint(this.getText(R.string.alternativo));
        etNumero4.setHint(this.getText(R.string.alternativo));
        foto = (ImageView) findViewById(R.id.fotoadd);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        aux=bundle.getParcelable("contacto");
        id=(long)bundle.getInt("pos");
        aux.setId(id);
        etNombre.setText(aux.getNombre());
        etNumero1.setText(aux.getNumero(0));

        if(aux.sizeNumeros()>1){
            etNumero2.setText(aux.getNumero(1));
        }
        if (aux.sizeNumeros()>2){
            etNumero3.setText(aux.getNumero(2));
        }
        if(aux.sizeNumeros()>3) {
            etNumero4.setText(aux.getNumero(3));
        }
        if (aux.getFoto()!=null) {
            imgFile = new File(aux.getFoto());
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                foto.setImageBitmap(myBitmap);
            }
        }

    }

    public void foto(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(
                            selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    rutaFoto=filePath;
                    File imgFile = new  File(filePath);
                    if(imgFile.exists()){
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        foto.setImageBitmap(myBitmap);
                    }
                }
        }
    }

    public void cancelar(View v){
        finish();
    }
    public void aceptar(View v){
        String test1, test2;
        test1=etNombre.getText().toString();
        test2=etNumero1.getText().toString();
        if (!test1.isEmpty()&&!test2.isEmpty()){
        aux.setId(id);
        aux.setNombre(etNombre.getText().toString());

        ArrayList<String> num=new ArrayList();
        num.add(etNumero1.getText().toString());

        if(etNumero2.getText().length()>0){
            num.add(etNumero2.getText().toString());
        }
        if(etNumero3.getText().length()>1){
            num.add(etNumero3.getText().toString());
        }
        if(etNumero4.getText().length()>2){
            num.add(etNumero4.getText().toString());
        }
        aux.setNumeros(num);
//        Agenda.setContacto(id, aux);
        aux.setFoto(rutaFoto);
        i=this.getIntent();
        Bundle bundle=new Bundle();
        bundle.putParcelable("contacto",aux);
        i.putExtras(bundle);
        setResult(Activity.RESULT_OK,i);
        finish();
    }else{
            Toast t=new Toast(this);
            t.makeText(this, this.getString(R.string.campo_vacio), Toast.LENGTH_LONG).show();}
    }
}
