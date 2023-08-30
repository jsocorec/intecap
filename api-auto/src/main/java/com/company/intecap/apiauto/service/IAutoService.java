package com.company.intecap.apiauto.service;

import com.company.intecap.apiauto.model.Auto;
import com.company.intecap.apiauto.response.AutoResponseRest;
import org.springframework.http.ResponseEntity;

public interface IAutoService {

    public ResponseEntity<AutoResponseRest> buscarAutos();

    public ResponseEntity<AutoResponseRest> buscarPorId(Long id);

    public ResponseEntity<AutoResponseRest> crearAuto(Auto auto);
}
