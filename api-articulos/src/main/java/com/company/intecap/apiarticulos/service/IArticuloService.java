package com.company.intecap.apiarticulos.service;

import com.company.intecap.apiarticulos.model.Articulo;
import com.company.intecap.apiarticulos.response.ArticuloResponseRest;
import org.springframework.http.ResponseEntity;

public interface IArticuloService {

    public ResponseEntity<ArticuloResponseRest> buscarTodosLosArticulos();

    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorId(Long id);

    public ResponseEntity<ArticuloResponseRest> crearArticulo(Articulo articulo);

    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(Articulo articulo, Long id);

    public ResponseEntity<ArticuloResponseRest> eliminarArticulo(Long id);
}
