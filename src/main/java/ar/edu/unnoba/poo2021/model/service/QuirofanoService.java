package ar.edu.unnoba.poo2021.model.service;

import java.util.List;
import ar.edu.unnoba.poo2021.model.entity.Quirofano;

public interface QuirofanoService {
    public Quirofano findById(Long id);
    public List<Quirofano> getQuirofanos();
}
