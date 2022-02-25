package ar.edu.unnoba.poo2021.model.service;

import java.util.List;
import ar.edu.unnoba.poo2021.model.entity.Profesional;

public interface ProfesionalService {
    public Profesional findById(Long id);
    public Profesional registrar(Profesional profesional) throws Exception;
    public List<Profesional> getProfesionales();
    public void delete(Long id);
}