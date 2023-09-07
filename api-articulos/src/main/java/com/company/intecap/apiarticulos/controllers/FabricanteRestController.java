package com.company.intecap.apiarticulos.controllers;

import com.company.intecap.apiarticulos.model.Fabricante;
import com.company.intecap.apiarticulos.response.FabricanteResponseRest;
import com.company.intecap.apiarticulos.service.IFabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FabricanteRestController {

    @Autowired
    private IFabricanteService service;

    @GetMapping("/fabricantes")//http://localhost:8080/api/v1/fabricantes
    public ResponseEntity<FabricanteResponseRest> buscarFabricantes(){
        return service.buscarTodosLosFabricantes();
    }

    @GetMapping("/fabricantes/{id}")//http://localhost:8080/api/v1/fabricantes/1
    public ResponseEntity<FabricanteResponseRest> buscarFabricantePorId(@PathVariable Long id){
        return service.buscarFabricantePorId(id);
    }

    @PostMapping("/fabricantes")//http://localhost:8080/api/v1/fabricantes
    public ResponseEntity<FabricanteResponseRest> crearFabricante(@RequestBody Fabricante request){
        return service.crearFabricante(request);
    }

    @PutMapping("/fabricantes/{id}")//http://localhost:8080/api/v1/fabricantes/1
    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(@RequestBody Fabricante request, @PathVariable Long id){
        return service.actualizarFabricante(request, id);
    }

    @DeleteMapping("/fabricantes/{id}")//http://localhost:8080/api/v1/fabricantes/1
    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(@PathVariable Long id){
        return service.eliminarFabricante(id);
    }

}
