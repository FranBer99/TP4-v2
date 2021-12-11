package ar.edu.unnoba.poo2021.model.repository;

import ar.edu.unnoba.poo2021.model.entity.Intervencion;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervencionRepository extends JpaRepository<Intervencion, Long> {

    @Query("select i" +
            "from Intervencion i" +
            "Where (fecha_hora_inicio > fechaHoraInicio and fecha_hora_inicio < fechaHoraFin) OR" +
            "  (fecha_hora_fin > fechaHoraFin and fecha_hora_inicio < fechaHoraInicio) OR" +
            "  (fecha_hora_fin > fechaHoraInicio and fecha_hora_fin < fechaHoraFin) OR" +
            "  (fecha_hora_fin < fechaHoraFin and fecha_hora_inicio > fechaHoraInicio);")
    public List<Intervencion> findBetweenFechas(@Param("fechaHoraInicio") LocalDateTime fechaHoraInicio, @Param("fechaHoraFin") LocalDateTime fechaHoraFin);
}

