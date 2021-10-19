package com.estacionamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.model.Cliente;
import com.estacionamento.repository.ClienteRepository;

@Service
public class ClienteService implements ServiceInterface<Cliente>{
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public Cliente create(Cliente obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElse(null);
	}

	@Override
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Cliente obj) {
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
