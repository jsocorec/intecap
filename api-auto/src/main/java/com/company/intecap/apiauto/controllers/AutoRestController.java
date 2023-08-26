package com.company.intecap.apiauto.controllers;

import com.company.intecap.apiauto.response.AutoResponseRest;
import com.company.intecap.apiauto.service.IAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1") //http://localhost:8080/api/v1
public class AutoRestController {

    @Autowired
    private IAutoService service;

    @GetMapping("/autos") //http://localhost:8080/api/v1/autos
    public AutoResponseRest buscarAutos(){
        return service.buscarAutos();
    }
}
