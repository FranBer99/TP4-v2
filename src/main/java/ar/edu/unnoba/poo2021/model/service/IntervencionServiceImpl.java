package ar.edu.unnoba.poo2021.model.service;

import ar.edu.unnoba.poo2021.model.entity.Intervencion;
import ar.edu.unnoba.poo2021.model.entity.Quirofano;
import ar.edu.unnoba.poo2021.model.repository.IntervencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IntervencionServiceImpl implements IntervencionService{

    @Autowired
    IntervencionRepository intervencionRepository;

    @Override
    public Intervencion findById(Long id) {
        return intervencionRepository.findById(id).get();
    }

    @Override
    public Intervencion registrar(Intervencion intervencion, Quirofano quirofano) throws Exception{
    	List<Intervencion> intervenciones = findBetweenFechaHora(intervencion.getFechaHoraInicio(), intervencion.getFechaHoraFin());
        if(intervenciones != null){
        	for(Intervencion inter : intervenciones) {
        		if(inter.getQuirofano().getId() == quirofano.getId()) {
        			throw new Exception("Esta fecha no esta disponible, pruebe otra");
        		}
        	}
        }
        return intervencionRepository.save(intervencion);
    }

    @Override
    public List<Intervencion> getIntervenciones() {
        return  intervencionRepository.findAll();
    }

    @Override
    public Intervencion update(Intervencion intervencion) {
        Intervencion iDB = intervencionRepository.findById(intervencion.getId()).get();
        iDB.setDescripcion(intervencion.getDescripcion());
        return intervencionRepository.save(iDB);
    }

    @Override
    public void delete(Long id) {
        intervencionRepository.delete(findById(id));
    }

    @Override
    public List<Intervencion> findBetweenFechaHora(Date inicio, Date fin) {
    	List<Intervencion> intervenciones = new ArrayList<>();
        for(Intervencion i : getIntervenciones()){
        	if(inicio.equals(i.getFechaHoraInicio()) || fin.equals(i.getFechaHoraFin())){
        		intervenciones.add(i); }
            if(inicio.after(i.getFechaHoraInicio()) && (inicio.before(i.getFechaHoraFin()))){
            	intervenciones.add(i); }
            if(inicio.before(i.getFechaHoraInicio()) && (fin.after(i.getFechaHoraInicio()))){
            	intervenciones.add(i); }
            if(inicio.before(i.getFechaHoraFin()) && (fin.after(i.getFechaHoraFin()))){
            	intervenciones.add(i); }
            if(inicio.before(i.getFechaHoraInicio()) && (fin.after(i.getFechaHoraFin()))){
            	intervenciones.add(i); }
        }
        return intervenciones;
    }
    
    @Override
    public List<Intervencion> getIntervencionesOrdenadas(){
    	List<Intervencion> intervenciones = intervencionRepository.findAll();
    	intervenciones.sort((o1, o2) -> o2.getFechaHoraInicio().compareTo(o1.getFechaHoraInicio()));
    	return intervenciones;
    }
    
}