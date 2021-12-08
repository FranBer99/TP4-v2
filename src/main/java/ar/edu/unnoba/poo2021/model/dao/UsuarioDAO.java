package ar.edu.unnoba.poo2021.model.dao;

import ar.edu.unnoba.poo2021.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    public Usuario findByEmail(String email);
}
