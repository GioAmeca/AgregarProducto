package com.unidad3.gio.agregarproducto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public TextView TxtCategoria;
    private ListView TxtLista;

    private List<String> productos= new ArrayList<>();
    private List<String> categoria= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TxtCategoria=(TextView)findViewById(R.id.TxtCategoria);
        TxtLista=(ListView) findViewById(R.id.TxtListaProductos);
         llenartabla();
    }
    //metodo boton
    public void LLamadaActividades(View x){
      Intent s= new Intent(this,AgregarActividad.class);
      startActivityForResult(s,123);
    }
    public void llenartabla(){
        String ProductosS[]=new String[productos.size()];
        productos.toArray(ProductosS);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ProductosS);
        TxtLista.setAdapter(adapter);

    TxtLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            TxtCategoria.setText("Categoria: " + categoria.get(i));
        }
    });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        productos.add(data.getStringExtra("name"));
        categoria.add(data.getStringExtra("categori"));
        llenartabla();
        Toast.makeText(MainActivity.this,"se Agrego: "+ data.getStringExtra("name"),Toast.LENGTH_LONG).show();
    }
}
