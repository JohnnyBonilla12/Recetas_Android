package com.example.proyecto_rez;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ingredientesActivity  extends AppCompatActivity {
    //Creacion de array de listas
    private RecyclerView recyclerView;
    private TextView nombreLista;
    private RecyclerView.Adapter adapter;
    //Alternativa no tan eficiente para recuperar informacion por medio del id
    //tendriamos que declarar algo que pueda recuperarlo confrome se crea la aplicacion
    //Asignandole un numero de acuerdo al login al usuario o algo similar
    configDB config =new configDB();
    String nombre;
    ProgressDialog progressDialog;
    ListView listview;
    List<String> listItems;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nombreLista =  findViewById(R.id.nombreLista);

        setContentView(R.layout.activity_listaingredientes);
        //Se declara por id el recycler view
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(ingredientesActivity.this));
        progressDialog = new ProgressDialog(this);
        listItems = new ArrayList<>();
        getIngredientes();
    }

    
    
    private void getIngredientes(){
        listItems.clear();
        //Declara el adaptador
        adapter = new AdapterIngre(getApplicationContext(),listItems,nombre);
        recyclerView.setAdapter(adapter);
        progressDialog.show();
        //Se tiene que añadir la libreria volley al build.gradel implementation 'com.android.volley:volley:1.1.1'
        //Lo que hace esta libreria es en esencia  para que pueda jalar los metodos del php

        StringRequest stringRequest = new StringRequest(Request.Method.GET
                //Aqui solo estamos utilizando la direccion de la pagina de php a la que le mandaremos el metodo
                //Se necesita mejorar la forma de envio del int para que pueda recuperar el id de la lista
                //esta es solo una solucion temporal
                , config.returnListaId(5)
                , new Response.Listener<String>() {
            //
            @Override
            public void onResponse(String response) {
        // Que haces una vez obtienes la respuesta
                try {

                    JSONObject array = new JSONObject(response.replaceAll("[^\\x00-\\x7F]", ""));


                        //Obtengo la variable nombre del post mandado

                            //por alguna razon no me deja poner el nombre de la lista en el textview
                       // nombre =array.getString("nombre");
                        //nombreLista.setText("Lista:"+nombre);

                         //Obtengo el array del post mandado
                    //modifico algunas cosas del string que me mando el json ,remplazando todos los elementos que no sean letras
                   String stringaux= array.getString("ingredientes").replaceAll("[\"\\[\\]]","");
                    //divido el string conforme a las comas y lo meto en un array para poder añadirlo 1x1 a las listas
                    String ingredientes[] =   stringaux.split(",");
                    Log.d("STATE", Arrays.toString(ingredientes));

                          //Dale un printl para probar si sale chido

                        //Crea un for para desmenusar el array de ingredientes y agregarlos a la lista de strings

                        for (int i = 0; i<ingredientes.length; i++){

                            listItems.add(ingredientes[i]);
                            //Manda al adaptador las variables definidas
                            adapter = new AdapterIngre(ingredientesActivity.this,listItems,nombre);
                            recyclerView.setAdapter(adapter);

                        }




                } catch (Exception e){
                        e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            //Que sucede en el caso de haber un error al obtener la respuesta
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ingredientesActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        Volley.newRequestQueue(ingredientesActivity.this).add(stringRequest);
    }





    }


