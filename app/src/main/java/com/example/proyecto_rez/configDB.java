package com.example.proyecto_rez;

public class configDB {

    public static final String URL= "http://192.168.1.3/";
    public static final String ROOT_URL = URL+"Recetas/ArchivosPHP/";
    public static final String URL_SELECT_ID = ROOT_URL+"obtenerIngredientes.php";

    public String returnListaId(int id){
       String URLIDIngredientes=URL_SELECT_ID+"?id="+id;

       return URLIDIngredientes;

    }

}
