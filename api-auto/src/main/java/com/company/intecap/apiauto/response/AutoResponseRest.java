package com.company.intecap.apiauto.response;

public class AutoResponseRest extends ResponseRest{

    private AutoResponse autoResponse = new AutoResponse();

    public AutoResponse getAutoResponse() {
        return autoResponse;
    }

    public void setAutoResponse(AutoResponse autoResponse){
        this.autoResponse = autoResponse;
    }
}
