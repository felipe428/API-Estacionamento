package com.estacionamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.model.Carro;
import com.estacionamento.repository.CarroRepository;

@Service
public class CarroService implements ServiceInterface<Carro>{

	@Autowired
	private CarroRepository repository;
	
	@Override
	public Carro create(Carro obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public Carro findById(Long id) {
		Optional<Carro> obj = repository.findById(id);
		return obj.orElse(null);
	}

	@Override
	public List<Carro> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Carro obj) {
		if(repository.existsById(obj.getId())) {
			repository.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

}
