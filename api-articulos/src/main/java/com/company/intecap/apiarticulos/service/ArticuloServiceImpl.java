package com.company.intecap.apiarticulos.service;

import com.company.intecap.apiarticulos.dao.IArticuloDao;
import com.company.intecap.apiarticulos.dao.IFabricanteDao;
import com.company.intecap.apiarticulos.model.Articulo;
import com.company.intecap.apiarticulos.response.ArticuloResponseRest;
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
public class ArticuloServiceImpl implements IArticuloService{

    //capturar errores en la consola
    private static final Logger log = LoggerFactory.getLogger(ArticuloServiceImpl.class);

    @Autowired
    private IArticuloDao articuloDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ArticuloResponseRest> buscarTodosLosArticulos() {
        log.info("Inicia metodo de buscarTodosLosArticulos");

        ArticuloResponseRest response = new ArticuloResponseRest();

        try{
            List<Articulo> articulo = (List<Articulo>) articuloDao.findAll();
            response.getArticuloResponse().setArticulos(articulo);
        }catch (Exception e){
            response.setMetadata("Respuesta no ok", "500", "Error al consultar articulos");
            log.error("Error al consultar articulos {}", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorId(Long id) {
        log.info("Inicia metodo de buscarArticulosPorId");

        ArticuloResponseRest response = new ArticuloResponseRest();
        List<Articulo> list = new ArrayList<>();

        try{
            Optional<Articulo> articulo = articuloDao.findById(id);

            if (articulo.isPresent()){
                list.add(articulo.get());
                response.getArticuloResponse().setArticulos(list);
            }else{
                log.error("Error al consultar articulo {} ", id);
                response.setMetadata("Respuesta no ok", "404", "Articulo no encontrado");
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al consultar articulo", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al consultar articulo, sin respuesta del servidor");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> crearArticulo(Articulo articulo) {
        log.info("inicio del metodo crearArticulo() Articulo");

        ArticuloResponseRest response = new ArticuloResponseRest();
        List<Articulo> list = new ArrayList<>();

        try{
            Articulo articuloGuardado = articuloDao.save(articulo);

            if (articuloGuardado != null){
                list.add(articuloGuardado);
                response.getArticuloResponse().setArticulos(list);
            }else {
                log.error("Error al crear articulo {}", articulo.toString());
                response.setMetadata("Respuesta no ok", "400", "Articulo no creado, error en el proceso");
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            log.error("Error al crear articulo", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al crear Articullo, servidor no responde");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "200", "Articulo creado correctamente");
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(Articulo articulo, Long id) {
        log.info("inicio de metodo actualizarArticulo()");

        ArticuloResponseRest response = new ArticuloResponseRest();
        List<Articulo> list = new ArrayList<>();

        try{
            Optional<Articulo> articuloBuscado = articuloDao.findById(id);

            if (articuloBuscado.isPresent()){
                articuloBuscado.get().setName(articulo.getName());
                articuloBuscado.get().setPrecio(articulo.getPrecio());

                Articulo articuloActualizado = articuloDao.save(articuloBuscado.get());

                if (articuloActualizado != null){
                    list.add(articuloActualizado);
                    response.getArticuloResponse().setArticulos(list);
                }else {
                    log.error("Error al actualizar articulo {}", articulo.toString());
                    response.setMetadata("Respuesta no ok", "400", "Articulo no actualizado");
                    return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }else{
                log.error("Error al actualizar articulo {} ", articulo.toString());
                response.setMetadata("Respuesta no ok", "400", "Articulo no actualizado");
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error al actualizar articulo", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al actualizar articulo");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "200", "Articulo actualizado con exito");
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> eliminarArticulo(Long id) {
        log.info("Inicia metodo eliminarArticulo");

        ArticuloResponseRest response = new ArticuloResponseRest();

        try{
            Optional<Articulo> articulo = articuloDao.findById(id);

            if (articulo.isPresent()){
                articuloDao.delete(articulo.get());
            }else{
                log.error("Error al eliminar articulo {}", id);
                response.setMetadata("Respuesta no ok", "400", "Articulo no eliminado, no existe");
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al eliminar articulo {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al eliminar articulo, error en el servidor");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "200", "Articulo eliminado");
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }
}
