package com.company.intecap.apiauto.response;

import com.company.intecap.apiauto.model.Auto;

import java.util.List;

public class AutoResponse {

    private List<Auto> autos;
    public List<Auto> getAutos(){
        return autos;
    }

    public void setAutos(List<Auto> autos){
        this.autos = autos;
    }
}
