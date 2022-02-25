package ar.edu.unnoba.poo2021.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unnoba.poo2021.model.entity.Profesional;
import ar.edu.unnoba.poo2021.model.repository.ProfesionalRepository;

@Service
public class ProfesionalServiceImpl implements ProfesionalService{

    @Autowired
    ProfesionalRepository profesionalRepository;

    @Override
    public Profesional findById(Long id) {
        return profesionalRepository.findById(id).get();
    }

    @Override
    public Profesional registrar(Profesional profesional) throws Exception{
        if(profesional != null){
        	for(Profesional profe : profesionalRepository.findAll()) {
        		if(profe.getMatricula() == profesional.getMatricula()){
        			throw new Exception("No se pudo registrar el profesional, la matricula ya esta cargada");
        		}
        	}
        }
        return profesionalRepository.save(profesional);
    }

    @Override
    public List<Profesional> getProfesionales() {
        return  profesionalRepository.findAll();
    }

    @Override
    public void delete(Long id) {
    	profesionalRepository.delete(findById(id));
    }
    
}