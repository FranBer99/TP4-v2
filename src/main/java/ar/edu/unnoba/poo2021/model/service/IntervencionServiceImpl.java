package ar.edu.unnoba.poo2021.model.service;

import ar.edu.unnoba.poo2021.model.entity.Intervencion;
import ar.edu.unnoba.poo2021.model.repository.IntervencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Intervencion registrar(Intervencion intervencion) throws Exception{
        if(intervencionRepository.findAllBetweenDates(intervencion.getFechaHoraInicio(), intervencion.getFechaHoraFin()) != null){
            throw new Exception("Esta fecha no esta disponible, pruebe otra");
        }else{
            return intervencionRepository.save(intervencion);
        }
    }

    @Override
    public List<Intervencion> getIntervenciones() {
        return intervencionRepository.findAll();
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
}