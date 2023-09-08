package com.company.intecap.apiarticulos.dao;

import com.company.intecap.apiarticulos.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

    public Usuario findByNombreUsuario(String nombreUsuario);

    @Query("select u from Usuario u where u.nombreUsuario=?1")
    public Usuario findByNombreUsuario2(String nombreUsuario);
}
