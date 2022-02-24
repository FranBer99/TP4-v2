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
				/*
				for(Intervencion intervencion : intervencionService.getIntervencionesFiltradas2(quirofano.getIntervenciones(), fechaFiltro)) {
					if(intervencion.getFechaHoraInicio().getHours() >= 0 && intervencion.getFechaHoraInicio().getHours() < 4
					|| intervencion.getFechaHoraFin().getHours() >= 0 && intervencion.getFechaHoraFin().getHours() < 4
					|| ((intervencion.getFechaHoraInicio().getHours() < 24 && intervencion.getFechaHoraInicio().getDate() != intervencion.getFechaHoraFin().getDate()) && intervencion.getFechaHoraFin().getHours() >= 4)) {
						mquirofano.setRango0(intervencion);
					}
					if(intervencion.getFechaHoraInicio().getHours() >= 4 && intervencion.getFechaHoraInicio().getHours() < 8
					|| intervencion.getFechaHoraFin().getHours() >= 4 && intervencion.getFechaHoraFin().getHours() < 8
					|| (intervencion.getFechaHoraInicio().getHours() < 4 && intervencion.getFechaHoraFin().getHours() >= 8)) {
						mquirofano.setRango1(intervencion);
					}
					if(intervencion.getFechaHoraInicio().getHours() >= 8 && intervencion.getFechaHoraInicio().getHours() < 12
					|| intervencion.getFechaHoraFin().getHours() >= 8 && intervencion.getFechaHoraFin().getHours() < 12
					|| (intervencion.getFechaHoraInicio().getHours() < 8 && intervencion.getFechaHoraFin().getHours() >= 12)) {
						mquirofano.setRango2(intervencion);
					}
					if(intervencion.getFechaHoraInicio().getHours() >= 12 && intervencion.getFechaHoraInicio().getHours() < 16
					|| intervencion.getFechaHoraFin().getHours() >= 12 && intervencion.getFechaHoraFin().getHours() < 16
					|| (intervencion.getFechaHoraInicio().getHours() < 12 && intervencion.getFechaHoraFin().getHours() >= 16)) {
						mquirofano.setRango3(intervencion);
					}
					if(intervencion.getFechaHoraInicio().getHours() >= 16 && intervencion.getFechaHoraInicio().getHours() < 20
					|| intervencion.getFechaHoraFin().getHours() >= 16 && intervencion.getFechaHoraFin().getHours() < 20
					|| (intervencion.getFechaHoraInicio().getHours() < 16 && intervencion.getFechaHoraFin().getHours() >= 20)) {
						mquirofano.setRango4(intervencion);
					}
					if(intervencion.getFechaHoraInicio().getHours() >= 20 && intervencion.getFechaHoraInicio().getHours() < 24
					|| intervencion.getFechaHoraFin().getHours() >= 20 && intervencion.getFechaHoraFin().getHours() < 24
					|| (intervencion.getFechaHoraInicio().getHours() < 20 && (intervencion.getFechaHoraFin().getHours() >= 0 && intervencion.getFechaHoraInicio().getDate() != intervencion.getFechaHoraFin().getDate()))) {
						mquirofano.setRango5(intervencion);
					}
				}
				*/
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
}
