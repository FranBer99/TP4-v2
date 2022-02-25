package ar.edu.unnoba.poo2021.model.service;

import java.util.Date;
import java.util.List;

import ar.edu.unnoba.poo2021.dto.MQuirofano;
import ar.edu.unnoba.poo2021.model.entity.Quirofano;

public interface QuirofanoService {
    public Quirofano findById(Long id);
    public List<Quirofano> getQuirofanos();
    public List<MQuirofano> getMatriz(Date fechaFiltro);
	public Object registrar(Quirofano quirofano);
	public void delete(Long quirofanoId);
}
