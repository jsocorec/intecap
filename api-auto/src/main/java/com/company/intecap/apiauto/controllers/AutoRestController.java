package com.company.intecap.apiauto.controllers;

import com.company.intecap.apiauto.model.Auto;
import com.company.intecap.apiauto.response.AutoResponseRest;
import com.company.intecap.apiauto.service.IAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1") //http://localhost:8080/api/v1
public class AutoRestController {

    @Autowired
    private IAutoService service;

    @GetMapping("/autos") //http://localhost:8080/api/v1/autos
    public ResponseEntity<AutoResponseRest> buscarAutos(){
        return service.buscarAutos();
    }

    @GetMapping("/autos/{id}")//http://localhost:8080/api/v1/autos/1
    public ResponseEntity<AutoResponseRest> buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PostMapping("/autos")//http://localhost:8080/api/v1/autos
    public ResponseEntity<AutoResponseRest> crearAuto(@RequestBody Auto request){
        return service.crearAuto(request);
    }
}
