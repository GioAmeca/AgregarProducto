package com.unidad3.gio.agregarproducto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

public class AgregarActividad extends AppCompatActivity {
     private EditText editar;
     private Spinner seleccionar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_actividad);
        String categorias[] ={"Bebe","Auto","Electronico","Peliculas","Ropa","Zapatos","Deporte ",
                "Hogar", "Industria", "Juegos", "Libros","Mascotas", "Musica"};
        editar=(EditText)findViewById(R.id.EdtxtProducto);
        seleccionar=(Spinner)findViewById(R.id.SCategoria);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_selectable_list_item,categorias);
        seleccionar.setAdapter(adapter);
        Arrays.sort(categorias);

        Intent i = getIntent();
        String modi=i.getStringExtra("producto");
        String cate=i.getStringExtra("categoria");

        editar.setText(modi);
        if (modi!=null) {
            int cat = Arrays.binarySearch(categorias, cate);
            seleccionar.setSelection(cat);
        }
    }

    public void Guardar(View w){
        Intent a = new  Intent();
        a.putExtra("name",editar.getText().toString());
        a.putExtra("categori",seleccionar.getSelectedItem().toString());
        setResult(RESULT_OK,a);
        finish();
    }



    //funcion boton atras
    public void LLamadaMain(View x){
        Intent a= new  Intent(this,MainActivity.class);
        startActivity(a);
    }

}
