package com.company.intecap.apiarticulos.service;

import com.company.intecap.apiarticulos.dao.IFabricanteDao;
import com.company.intecap.apiarticulos.model.Fabricante;
import com.company.intecap.apiarticulos.response.FabricanteResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FabricanteServiceImpl implements IFabricanteService{

    private static final Logger log = LoggerFactory.getLogger(FabricanteServiceImpl.class);

    @Autowired
    private IFabricanteDao fabricanteDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FabricanteResponseRest> buscarTodosLosFabricantes() {
        log.info("Inicia metodo buscarTodosLosFabricantes()");
        FabricanteResponseRest response = new FabricanteResponseRest();

        try{
            List<Fabricante> fabricante = (List<Fabricante>) fabricanteDao.findAll();
            response.getFabricanteResponse().setFabricantes(fabricante);
            response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        }catch (Exception e){
            response.setMetadata("1 Respuesta no ok", "500", "Error al consultar fabricantes");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FabricanteResponseRest> buscarFabricantePorId(Long id) {
        log.info("Inicia metodo buscarFabricanteporId()");
        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();

        try{
            Optional<Fabricante> fabricante = fabricanteDao.findById(id);

            if (fabricante.isPresent()){
                list.add(fabricante.get());
                response.getFabricanteResponse().setFabricantes(list);
            }else{
                log.error("1 Error al consultar fabricante por Id {}", id);
                response.setMetadata("2 Respuesta no ok", "400", "Fabricante no encontrado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("2 Error al consultar fabricante {}", e.getMessage());
            response.setMetadata("3 Respuesta no ok", "500", "Error al consultar fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("4 Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> crearFabricante(Fabricante fabricante) {
        log.info("Inicia el metodo de crearFabricante()");

        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();

        try{
            Fabricante fabricanteGuardado = fabricanteDao.save(fabricante);

            if (fabricanteGuardado != null){
                list.add(fabricanteGuardado);
                response.getFabricanteResponse().setFabricantes(list);
            }else{
                log.error("1 Error al crear fabricante {} ", fabricante.toString());
                response.setMetadata("2 Respuesta no ok", "400", "Fabricante no creado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("3 Error al crear fabricante {} ", e.getMessage());
            response.setMetadata("4 Respuesta no ok", "500", "Error al crear fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("5 Respuesta ok", "200", "Fabricante creado correctamente");
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(Fabricante fabricante, Long id) {
        log.info("Inicia metodo actualizarFabricante () fabricante");

        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();

        try{
            Optional<Fabricante> fabricanteBuscado = fabricanteDao.findById(id);

            if (fabricanteBuscado.isPresent()){
                fabricanteBuscado.get().setNombre(fabricante.getNombre());

                Fabricante fabricanteActualizado = fabricanteDao.save(fabricanteBuscado.get());

                if (fabricanteActualizado != null){
                    list.add(fabricanteActualizado);
                    response.getFabricanteResponse().setFabricantes(list);
                }else {
                    log.error("1 Error al actualizar fabricante {} ", fabricante.toString());
                    response.setMetadata("2 Respuesta no ok", "400", "Fabricante no actualizado");
                    return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }else {
                log.error("3 Error al actualizar fabricante {} ", fabricante.toString());
                response.setMetadata("4 Respuesta no ok", "400", "Fabricante no actualizado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("5 Error al actualizar fabricante {}", e.getMessage());
            response.setMetadata("6 Respuesta no ok", "500", "Error al actualizar fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "200", "Fabricante actualizado");
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(Long id) {
        log.info("Inicia metodo de eliminarFabricante");

        FabricanteResponseRest response = new FabricanteResponseRest();

        try{
            Optional<Fabricante> fabricante = fabricanteDao.findById(id);

            if (fabricante.isPresent()){
                fabricanteDao.delete(fabricante.get());
            }else{
                log.error("Error al eliminar fabricante {} ", id);
                response.setMetadata("Respuesta no ok", "400", "Fabricante no eliminado ya que no existe");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al eliminar fabricante {} ", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al eliminar fabricante, sin respuesta del servidor");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "200", "Fabricante eliminado");
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }
}
