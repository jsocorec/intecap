package com.company.intecap.apiauto.dao;

import com.company.intecap.apiauto.model.Auto;
import org.springframework.data.repository.CrudRepository;

public interface IAutoDao extends CrudRepository<Auto, Long> {
}
