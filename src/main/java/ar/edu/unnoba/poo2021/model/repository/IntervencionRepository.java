package ar.edu.unnoba.poo2021.model.repository;

import ar.edu.unnoba.poo2021.model.entity.Intervencion;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervencionRepository extends JpaRepository<Intervencion, Long> {
	
	@Query("SELECT i FROM Intervencion i WHERE (?1 BETWEEN i.fechaHoraInicio AND i.fechaHoraFin) "
			+ "OR (?2 BETWEEN i.fechaHoraInicio AND i.fechaHoraFin) "
			+ "OR (?1 < i.fechaHoraInicio AND ?2 > i.fechaHoraFin)")
    List<Intervencion> findAllBetweenDates(Date fechaInicio, Date fechaFinal);
}

