package com.estacionamento.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
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
		obj.setHoraEntrada(LocalTime.now(ZoneId.of("America/Sao_Paulo")));
		obj.setDataEntrada(LocalDate.now((ZoneId.of("America/Sao_Paulo"))));
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
	
	public Estacionamento saidaEstacionamento(Long id) {
		if(repository.existsById(id)) {
			Estacionamento obj = findById(id);
			obj.setHoraSaida(LocalTime.now(ZoneId.of("America/Sao_Paulo")));
			obj.setDataSaida(LocalDate.now((ZoneId.of("America/Sao_Paulo"))));
			Integer valor = obj.getHoraSaida().getMinute() - obj.getHoraEntrada().getMinute();
			System.out.println(valor);
			repository.save(obj);
			obj.setPreco(0.50 * valor);
			repository.save(obj);
			return obj;
		}
		return null;
	}

}
