package com.company.intecap.apiarticulos.controllers;

import com.company.intecap.apiarticulos.model.Articulo;

import com.company.intecap.apiarticulos.response.ArticuloResponseRest;
import com.company.intecap.apiarticulos.service.IArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ArticuloRestController {

    @Autowired
    private IArticuloService service;

    @GetMapping("/articulos") //http://localhost:8080/api/v1/articulos
    public ResponseEntity<ArticuloResponseRest> buscarArticulos(){
        return service.buscarTodosLosArticulos();
    }

    @GetMapping("/articulos/{id}") //http://localhost:8080/api/v1/articulos/1
    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorId(@PathVariable Long id){
        return service.buscarArticuloPorId(id);
    }

    @PostMapping("/articulos") //http://localhost:8080/api/v1/articulos
    public ResponseEntity<ArticuloResponseRest> crearArticulo(@RequestBody Articulo articulo){
        return service.crearArticulo(articulo);
    }

    @PutMapping("/articulos/{id}") //http://localhost:8080/api/v1/articulos/1
    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(@RequestBody Articulo articulo, @PathVariable Long id){
        return service.actualizarArticulo(articulo, id);
    }

    @DeleteMapping("/articulos")//http://localhost:8080/api/v1/articulos/1
    public ResponseEntity<ArticuloResponseRest> eliminarArticulo(@PathVariable Long id){
        return service.eliminarArticulo(id);
    }
}
