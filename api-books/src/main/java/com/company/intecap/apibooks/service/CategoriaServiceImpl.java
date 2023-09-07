package com.company.intecap.apibooks.service;

import com.company.intecap.apibooks.dao.ICategoriaDao;
import com.company.intecap.apibooks.model.Categoria;
import com.company.intecap.apibooks.response.CategoriaResponseRest;
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
public class CategoriaServiceImpl implements ICategoriaService{
    //errores capturar en la consola
    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    @Autowired
    private ICategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
        //aqui se implementa la logica del negocio
        log.info("inicio de metodo buscarCategorias()");
        CategoriaResponseRest response = new CategoriaResponseRest();

        try{
            List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
            response.getCategoriaResponse().setCategorias(categoria);
            response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        }catch(Exception e){
            response.setMetadata("Respuesta no ok", "500", "Error al consultar");
            log.error("Error al consultar categorias{]", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {
        log.info("inicio metodo buscarPorId");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();

        try{
            Optional<Categoria> categoria = categoriaDao.findById(id);

            if (categoria.isPresent()){
                list.add(categoria.get());
                response.getCategoriaResponse().setCategorias(list);
            }else {
                log.error("Error al consultar categoria {} ", id);
                response.setMetadata("Respuesta no ok", "404", "Categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al consultar categoria {} ", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al consultar categoria");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta Ok", "200", "Respuesta exitosa");
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
        log.info("inicio metodo crear() Categoria");

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();

        try{
            Categoria categoriaGuardada = categoriaDao.save(categoria);

            if(categoriaGuardada != null){
                list.add(categoriaGuardada);
                response.getCategoriaResponse().setCategorias(list);
            }else {
                log.error("Error al crear categoria {} ", categoria.toString());
                response.setMetadata("Respuesta no ok", "400", "Categoria no creada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error al crear categoria {} ", e.getMessage());
            response.setMetadata("Respuesta no Ok", "400", "Categoria no creada");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {
        log.info("inicio metodo actualizar {} Categoria");

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();

        try{
            Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);

            if (categoriaBuscada.isPresent()){
                categoriaBuscada.get().setNombre(categoria.getNombre());
                categoriaBuscada.get().setDescripcion(categoria.getDescripcion());
                Categoria categoriaActualizada = categoriaDao.save(categoriaBuscada.get());

                if (categoriaActualizada != null){
                    list.add(categoriaActualizada);
                    response.getCategoriaResponse().setCategorias(list);
                }else{
                    log.error("Error al actualizar categoria {} ", categoria.toString());
                    response.setMetadata("Respuesta no ok", "400", "Categoria no actualizada");
                    return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }else{
                log.error("Error al actualizar categoria {} ", categoria.toString());
                response.setMetadata("Respuesta no ok", "400", "Categoria no actualizada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error al actualizar categoria {} ", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al actualizar categoria");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "200", "Categoria actualizada");
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
        log.info("inicio de metodo eliminar () categoria");
        CategoriaResponseRest response = new CategoriaResponseRest();

        try{
            Optional<Categoria> categoria = categoriaDao.findById(id);

            if (categoria.isPresent()){
                categoriaDao.delete(categoria.get());
            }else{
                log.error("Error al eliminar categoria {} ", id);
                response.setMetadata("Respuesta no ok", "400", "Categoria no eliminada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al eliminar categoria {} ", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al eliminar categoria");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "200", "Categoria eliminada");
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }
}
