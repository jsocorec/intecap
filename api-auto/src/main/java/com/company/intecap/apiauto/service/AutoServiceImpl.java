package com.company.intecap.apiauto.service;

import com.company.intecap.apiauto.dao.IAutoDao;
import com.company.intecap.apiauto.model.Auto;
import com.company.intecap.apiauto.response.AutoResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoServiceImpl implements IAutoService{
    // capturar errores en la consola

    private static final Logger log = LoggerFactory.getLogger(AutoServiceImpl.class);

    @Autowired
    private IAutoDao autoDao;

    @Autowired
    public AutoResponseRest buscarAutos(){
        //implementacion de la logica del negocio
        log.info("inicio de metodo buscarAutos()");
        AutoResponseRest response = new AutoResponseRest();

        try {
            List<Auto> auto = (List<Auto>) autoDao.findAll();
            response.getAutoResponse().setAutos(auto);
            response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        }catch (Exception e){
            response.setMetadata("Respuesta no ok", "500", "Error al consultar");
            log.error("Error al consultar autos()", e.getMessage());
            e.getStackTrace();
        }

        return null;
    }
}
