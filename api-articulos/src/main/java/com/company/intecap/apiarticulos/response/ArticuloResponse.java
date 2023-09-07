package com.company.intecap.apiarticulos.response;

import com.company.intecap.apiarticulos.model.Articulo;

import java.util.List;

public class ArticuloResponse {

    private List<Articulo> articulos;

    public List<Articulo> getArticulos(){
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }
}
