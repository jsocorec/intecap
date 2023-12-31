package com.company.intecap.apiauto.service;

import com.company.intecap.apiauto.dao.IAutoDao;
import com.company.intecap.apiauto.model.Auto;
import com.company.intecap.apiauto.response.AutoResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutoServiceImpl implements IAutoService{
    // capturar errores en la consola

    private static final Logger log = LoggerFactory.getLogger(AutoServiceImpl.class);

    @Autowired
    private IAutoDao autoDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<AutoResponseRest> buscarAutos(){
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
            return new ResponseEntity<AutoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AutoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<AutoResponseRest> buscarPorId(Long id) {
        log.info("Inicio de metodo buscarPorId");
        AutoResponseRest response = new AutoResponseRest();
        List<Auto> list = new ArrayList<>();

        try{
            Optional<Auto> auto = autoDao.findById(id);

            if (auto.isPresent()){
                list.add(auto.get());
                response.getAutoResponse().setAutos(list);
            }else {
                log.error("Error al consultar auto {} ",id);
                response.setMetadata("Respuesta no ok", "404", "Auto no encontrado");
                return new ResponseEntity<AutoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al consultar auto {} ", e.getMessage());
            response.setMetadata("Respuesta no ok","500", "Error al consultar Auto");
            return new ResponseEntity<AutoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<AutoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<AutoResponseRest> crearAuto(Auto auto) {
        log.info("Inicio metodo crear() Auto");

        AutoResponseRest response = new AutoResponseRest();
        List<Auto> list = new ArrayList<>();

        try{
            Auto autoGuardado = autoDao.save(auto);

            if(autoGuardado != null){
                list.add(autoGuardado);
                response.getAutoResponse().setAutos(list);
            }else {
                log.error("Error al crear auto {} ", auto.toString());
                response.setMetadata("Respuesta no ok", "400", "Auto no creado");
                return new ResponseEntity<AutoResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error al crear auto {} ", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al crear auto");
            return new ResponseEntity<AutoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AutoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AutoResponseRest> actualizar(Auto auto, Long id) {
        log.info("Inicia método actualizar() Auto");

        AutoResponseRest response = new AutoResponseRest();
        List<Auto> list = new ArrayList<>();

        try{
            Optional<Auto> autoBuscado = autoDao.findById(id);

            if (autoBuscado.isPresent()){
                //Seteamos los datos que vienen en el objeto
                autoBuscado.get().setAsientos(auto.getAsientos());
                autoBuscado.get().setLinea(auto.getLinea());
                autoBuscado.get().setMarca(auto.getMarca());
                autoBuscado.get().setModelo(auto.getModelo());
                autoBuscado.get().setTransmision(auto.getTransmision());

                Auto autoActualizado = autoDao.save(autoBuscado.get());

                if (autoActualizado != null){
                    list.add(autoActualizado);
                    response.getAutoResponse().setAutos(list);
                }else{
                    log.error("Error al actualizar auto {} ", auto.toString());
                    response.setMetadata("Respuesta no ok", "400", "Auto no actualizado");
                    return new ResponseEntity<AutoResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }else{
                log.error("Error al actualizar auto{} ", auto.toString());
                response.setMetadata("Respuesta no ok", "400", "Auto no actualizado");
                return new ResponseEntity<AutoResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error al actualizar auto {} ", e.getMessage());
            response.setMetadata("Respuesta no ok ", "500", "Error al actualizar Auto");
            return new ResponseEntity<AutoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok ", "200", "Auto Actualizado");
        return new ResponseEntity<AutoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AutoResponseRest> eliminar(Long id) {
        log.info("Inicio de metodo eliminar () auto");
        AutoResponseRest response = new AutoResponseRest();

        try{
            Optional<Auto> auto = autoDao.findById(id);

            if (auto.isPresent()){
                autoDao.delete(auto.get());
            }else{
                log.error("Error al eliminar auto {}", id);
                response.setMetadata("Respuesta no ok", "400", "Auto no eliminado");
                return new ResponseEntity<AutoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al eliminar auto {} ", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al eliminar auto");
            return new ResponseEntity<AutoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok ", "200", "Auto Eliminado");
        return new ResponseEntity<AutoResponseRest>(response, HttpStatus.OK);
    }

}
