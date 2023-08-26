package com.company.intecap.apibooks.response;

//Metadata es la informacion que se envia en el header de la peticion, para saber
//como respondio el servicio

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {

    private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();
    //clave valor
    //metodo publico que devuelve un arrayLists de hashmap

    public ArrayList<HashMap<String, String>> getMetadata(){
        return metadata;
    }

    public void setMetadata(String tipo, String codigo, String dato){
        HashMap<String, String> mapa = new HashMap<>();
        mapa.put("tipo", tipo);
        mapa.put("codigo", codigo);
        mapa.put("date", dato);
        metadata.add(mapa);
    }
}
