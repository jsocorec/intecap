package com.company.intecap.apiarticulos.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {

    private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadata(){
        return metadata;
    }

    public void setMetadata(String tipo, String codigo, String data){
        HashMap<String, String> mapa = new HashMap<>();
        mapa.put("tipo", tipo);
        mapa.put("codigo", codigo);
        mapa.put("data", data);
        metadata.add(mapa);
    }

}
