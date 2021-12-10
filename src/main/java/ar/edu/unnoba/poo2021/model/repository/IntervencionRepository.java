package ar.edu.unnoba.poo2021.model.repository;

import ar.edu.unnoba.poo2021.model.entity.Intervencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IntervencionRepository extends JpaRepository<Intervencion, Long> {
    public Intervencion findByID(Long id);
    public Intervencion findBetweenFechaHora(Date inicio, Date fin);
}

