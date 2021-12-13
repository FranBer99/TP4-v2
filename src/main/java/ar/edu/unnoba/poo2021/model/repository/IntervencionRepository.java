package ar.edu.unnoba.poo2021.model.repository;

import ar.edu.unnoba.poo2021.model.entity.Intervencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervencionRepository extends JpaRepository<Intervencion, Long> {
}

