package ar.edu.unnoba.poo2021.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unnoba.poo2021.model.entity.Quirofano;
import ar.edu.unnoba.poo2021.model.repository.QuirofanoRepository;

@Service
public class QuirofanoServiceImpl implements QuirofanoService{
	    @Autowired
	    QuirofanoRepository quirofanoRepository;

		@Override
		public Quirofano findById(Long id) {
			return quirofanoRepository.findById(id).get();
		}

		@Override
		public List<Quirofano> getQuirofanos() {
			return quirofanoRepository.findAll();
		}
}
