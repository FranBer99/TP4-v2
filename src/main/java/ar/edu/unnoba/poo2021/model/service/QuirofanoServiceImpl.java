package ar.edu.unnoba.poo2021.model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo2021.dto.MQuirofano;
import ar.edu.unnoba.poo2021.model.entity.Intervencion;
import ar.edu.unnoba.poo2021.model.entity.Quirofano;
import ar.edu.unnoba.poo2021.model.repository.QuirofanoRepository;

@Service
public class QuirofanoServiceImpl implements QuirofanoService{
		final int rango = 60*4;
	    @Autowired
	    QuirofanoRepository quirofanoRepository;
	    @Autowired
	    IntervencionService intervencionService;

		@Override
		public Quirofano findById(Long id) {
			return quirofanoRepository.findById(id).get();
		}

		@Override
		public List<Quirofano> getQuirofanos() {
			return quirofanoRepository.findAll();
		}
		
		@Override
		public List<MQuirofano> getMatriz(Date fechaFiltro) {
			List<MQuirofano> matriz = new ArrayList<>();
			List<Quirofano> quirofanos = getQuirofanos();
			for(Quirofano quirofano : quirofanos) {
				MQuirofano mquirofano = new MQuirofano();
				mquirofano.setQuirofano(quirofano);
				Calendar fechaRango1 = Calendar.getInstance(); fechaRango1.setTime(fechaFiltro); fechaRango1.add(Calendar.MINUTE, 0);
				Calendar fechaRango2 = Calendar.getInstance(); fechaRango2.setTime(fechaFiltro); fechaRango2.add(Calendar.MINUTE, rango);
				for(Intervencion intervencion : intervencionService.getIntervencionesFiltradas2(quirofano.getIntervenciones(), fechaFiltro)) {
					if((intervencion.getFechaHoraInicio().compareTo(fechaRango1.getTime()) >= 0 && intervencion.getFechaHoraInicio().compareTo(fechaRango2.getTime()) < 0) 
					|| (intervencion.getFechaHoraFin().compareTo(fechaRango1.getTime()) >= 0 && intervencion.getFechaHoraFin().compareTo(fechaRango2.getTime()) < 0)
					|| (intervencion.getFechaHoraInicio().compareTo(fechaRango1.getTime()) <= 0 && intervencion.getFechaHoraFin().compareTo(fechaRango2.getTime()) >= 0)){
						mquirofano.setRango0(intervencion);
					}
					fechaRango1.add(Calendar.MINUTE, rango); fechaRango2.add(Calendar.MINUTE, rango);
					if((intervencion.getFechaHoraInicio().compareTo(fechaRango1.getTime()) >= 0 && intervencion.getFechaHoraInicio().compareTo(fechaRango2.getTime()) < 0) 
					|| (intervencion.getFechaHoraFin().compareTo(fechaRango1.getTime()) >= 0 && intervencion.getFechaHoraFin().compareTo(fechaRango2.getTime()) < 0)
					|| (intervencion.getFechaHoraInicio().compareTo(fechaRango1.getTime()) <= 0 && intervencion.getFechaHoraFin().compareTo(fechaRango2.getTime()) >= 0)){
						mquirofano.setRango1(intervencion);
					}
					fechaRango1.add(Calendar.MINUTE, rango); fechaRango2.add(Calendar.MINUTE, rango);
					if((intervencion.getFechaHoraInicio().compareTo(fechaRango1.getTime()) >= 0 && intervencion.getFechaHoraInicio().compareTo(fechaRango2.getTime()) < 0) 
					|| (intervencion.getFechaHoraFin().compareTo(fechaRango1.getTime()) >= 0 && intervencion.getFechaHoraFin().compareTo(fechaRango2.getTime()) < 0)
					|| (intervencion.getFechaHoraInicio().compareTo(fechaRango1.getTime()) <= 0 && intervencion.getFechaHoraFin().compareTo(fechaRango2.getTime()) >= 0)){
						mquirofano.setRango2(intervencion);
					}
					fechaRango1.add(Calendar.MINUTE, rango); fechaRango2.add(Calendar.MINUTE, rango);
					if((intervencion.getFechaHoraInicio().compareTo(fechaRango1.getTime()) >= 0 && intervencion.getFechaHoraInicio().compareTo(fechaRango2.getTime()) < 0) 
					|| (intervencion.getFechaHoraFin().compareTo(fechaRango1.getTime()) >= 0 && intervencion.getFechaHoraFin().compareTo(fechaRango2.getTime()) < 0)
					|| (intervencion.getFechaHoraInicio().compareTo(fechaRango1.getTime()) <= 0 && intervencion.getFechaHoraFin().compareTo(fechaRango2.getTime()) >= 0)){
						mquirofano.setRango3(intervencion);
					}
					fechaRango1.add(Calendar.MINUTE, rango); fechaRango2.add(Calendar.MINUTE, rango);
					if((intervencion.getFechaHoraInicio().compareTo(fechaRango1.getTime()) >= 0 && intervencion.getFechaHoraInicio().compareTo(fechaRango2.getTime()) < 0) 
					|| (intervencion.getFechaHoraFin().compareTo(fechaRango1.getTime()) >= 0 && intervencion.getFechaHoraFin().compareTo(fechaRango2.getTime()) < 0)
					|| (intervencion.getFechaHoraInicio().compareTo(fechaRango1.getTime()) <= 0 && intervencion.getFechaHoraFin().compareTo(fechaRango2.getTime()) >= 0)){
						mquirofano.setRango4(intervencion);
					}
					fechaRango1.add(Calendar.MINUTE, rango); fechaRango2.add(Calendar.MINUTE, rango);
					if((intervencion.getFechaHoraInicio().compareTo(fechaRango1.getTime()) >= 0 && intervencion.getFechaHoraInicio().compareTo(fechaRango2.getTime()) < 0) 
					|| (intervencion.getFechaHoraFin().compareTo(fechaRango1.getTime()) >= 0 && intervencion.getFechaHoraFin().compareTo(fechaRango2.getTime()) < 0)
					|| (intervencion.getFechaHoraInicio().compareTo(fechaRango1.getTime()) <= 0 && intervencion.getFechaHoraFin().compareTo(fechaRango2.getTime()) >= 0)){
						mquirofano.setRango5(intervencion);
					}
				}
				matriz.add(mquirofano);
			}
			return matriz;
		}
		
		@Override
	    public Quirofano registrar(Quirofano quirofano) {
	        quirofanoRepository.save(quirofano);
	        return quirofano;
	    }

	    @Override
	    public void delete(Long id) {
	        quirofanoRepository.delete(findById(id));
	    }
}
