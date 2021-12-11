package ar.edu.unnoba.poo2021.model.repository;

import ar.edu.unnoba.poo2021.model.entity.Intervencion;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervencionRepository extends JpaRepository<Intervencion, Long> {
	
	//@Query("select i from Intervencion i where i.fecha_hora_inicio <= :creationDateTime")
    //List<Intervencion> findAllWithCreationDateTimeBefore(@Param("creationDateTime") Date creationDateTime);
}

