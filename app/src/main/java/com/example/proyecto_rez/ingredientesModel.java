package com.example.proyecto_rez;

public class ingredientesModel {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public ingredientesModel(String title, String ingrediente) {
        this.title = title;
        this.ingrediente = ingrediente;
    }

    private String title, ingrediente;


}
