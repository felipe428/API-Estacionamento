package com.estacionamento.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.model.Estacionamento;
import com.estacionamento.repository.EstacionamentoRepository;

@Service
public class EstacionamentoService implements ServiceInterface<Estacionamento>{

	@Autowired
	private EstacionamentoRepository repository;
	
	@Override
	public Estacionamento create(Estacionamento obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public Estacionamento findById(Long id) {
		Optional<Estacionamento> obj = repository.findById(id);
		return obj.orElse(null);
	}

	@Override
	public List<Estacionamento> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Estacionamento obj) {
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
	
	public Estacionamento saidaEstacionamento(Estacionamento obj) {
		if(repository.existsById(obj.getId())) {
			obj.setHoraSaida(LocalTime.now());
			repository.save(obj);
			return obj;
		}
		return null;
	}

}
