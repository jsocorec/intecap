package com.company.intecap.apibooks.service;

import com.company.intecap.apibooks.dao.ICategoriaDao;
import com.company.intecap.apibooks.model.Categoria;
import com.company.intecap.apibooks.response.CategoriaResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
    //errores capturar en la consola
    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    @Autowired
    private ICategoriaDao categoriaDao;

    @Override
    public CategoriaResponseRest buscarCategorias() {
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
        }

        return response;
    }
}
