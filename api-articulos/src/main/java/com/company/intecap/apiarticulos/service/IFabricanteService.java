package com.company.intecap.apiarticulos.service;

import com.company.intecap.apiarticulos.model.Fabricante;
import com.company.intecap.apiarticulos.response.FabricanteResponseRest;
import org.springframework.http.ResponseEntity;

public interface IFabricanteService {

    public ResponseEntity<FabricanteResponseRest> buscarTodosLosFabricantes();

    public ResponseEntity<FabricanteResponseRest> buscarFabricantePorId(Long id);

    public ResponseEntity<FabricanteResponseRest> crearFabricante(Fabricante fabricante);

    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(Fabricante fabricante, Long id);

    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(Long id);
}
