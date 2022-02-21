package ar.edu.unnoba.poo2021.model.service;
import ar.edu.unnoba.poo2021.model.entity.Intervencion;
import ar.edu.unnoba.poo2021.model.entity.Quirofano;

import java.util.Date;
import java.util.List;

public interface IntervencionService {
    public Intervencion findById(Long id);
    public Intervencion registrar(Intervencion intervencion, Quirofano quirofano) throws Exception;
    public List<Intervencion> getIntervenciones();
    public Intervencion update(Intervencion intervencion);
    public void delete(Long id);
    public List<Intervencion> findBetweenFechaHora(Date inicio, Date fin);
    public List<Intervencion> getIntervencionesOrdenadas();
}