package ar.edu.unnoba.poo2021.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unnoba.poo2021.model.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    public Rol findByNombre(String nombre); 
}