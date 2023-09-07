package com.company.intecap.apibooks.service;

import com.company.intecap.apibooks.model.Categoria;
import com.company.intecap.apibooks.response.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
    //ir a traer todas las categorias
    public ResponseEntity<CategoriaResponseRest> buscarCategorias();

    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id);

    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria);

    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id);

    public ResponseEntity<CategoriaResponseRest> eliminar(Long id);
}
