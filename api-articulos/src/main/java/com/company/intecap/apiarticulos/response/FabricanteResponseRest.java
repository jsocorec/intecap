package com.company.intecap.apiarticulos.response;

public class FabricanteResponseRest extends ResponseRest {

    private FabricanteResponse fabricanteResponse = new FabricanteResponse();

    public FabricanteResponse getFabricanteResponse(){
        return fabricanteResponse;
    }

    public void setFabricanteResponse(FabricanteResponse fabricanteResponse) {
        this.fabricanteResponse = fabricanteResponse;
    }
}
