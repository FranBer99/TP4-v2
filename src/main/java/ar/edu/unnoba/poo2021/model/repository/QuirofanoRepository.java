package ar.edu.unnoba.poo2021.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unnoba.poo2021.model.entity.Quirofano;

@Repository
public interface QuirofanoRepository extends JpaRepository<Quirofano, Long> {
}

