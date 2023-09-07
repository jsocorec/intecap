package com.company.intecap.apiarticulos.dao;

import com.company.intecap.apiarticulos.model.Articulo;
import org.springframework.data.repository.CrudRepository;

public interface IArticuloDao extends CrudRepository<Articulo, Long> {
}
