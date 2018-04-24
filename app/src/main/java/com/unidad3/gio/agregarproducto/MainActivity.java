package com.unidad3.gio.agregarproducto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    int pos;
    private List<String> productos= new ArrayList<>();
    private List<String> categoria= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TxtCategoria=(TextView)findViewById(R.id.TxtCategoria);
        TxtLista=(ListView) findViewById(R.id.TxtListaProductos);
         llenartabla();
         registerForContextMenu(TxtLista);
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
        if(requestCode==RESULT_OK) {
            if (requestCode == 123) {
                productos.add(data.getStringExtra("name"));
                categoria.add(data.getStringExtra("categori"));
                llenartabla();
                Toast.makeText(MainActivity.this, "se Agrego: " + data.getStringExtra("name"), Toast.LENGTH_LONG).show();
            }
            if (requestCode == 555) {
                productos.set(pos, data.getStringExtra("name"));
                categoria.set(pos, data.getStringExtra("categori"));
                llenartabla();
                Toast.makeText(this, "Se Modifico", Toast.LENGTH_LONG).show();
            }
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i=getMenuInflater();
        i.inflate(R.menu.menu_barra, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.opcion_salir:
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(TxtLista.getAdapter().getItem(info.position).toString());
        getMenuInflater().inflate(R.menu.menu_contextual,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int i= (int) TxtLista.getSelectedItemId();
        switch (item.getItemId()){
            case R.id.contextual_1:
                Intent m= new Intent(this,AgregarActividad.class);
                pos=i;
                m.putExtra("producto", productos.get(i).toString());
                m.putExtra("categoria",categoria.get(i).toString());
                startActivityForResult(m,555);
               // Toast.makeText(this,"Se Modifico",Toast.LENGTH_LONG).show();
                break;
            case R.id.contextual_2:
                productos.remove(i);
                categoria.remove(i);
                Toast.makeText(this,"Se Elimino",Toast.LENGTH_LONG).show();
                llenartabla();
        }
        return super.onContextItemSelected(item);
    }
}
