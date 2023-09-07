package com.company.intecap.apiarticulos.dao;

import com.company.intecap.apiarticulos.model.Fabricante;
import org.springframework.data.repository.CrudRepository;

public interface IFabricanteDao extends CrudRepository<Fabricante, Long> {
}
