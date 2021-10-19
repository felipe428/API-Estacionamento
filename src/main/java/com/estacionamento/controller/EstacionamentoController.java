package com.estacionamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamento.model.Estacionamento;
import com.estacionamento.service.EstacionamentoService;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController implements ControllerInterface<Estacionamento>{

	@Autowired
    EstacionamentoService service;

    @Override
    @GetMapping
    public ResponseEntity<List<Estacionamento>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Estacionamento _Estacionamento = service.findById(id);
        if(_Estacionamento != null) {
            return ResponseEntity.ok(_Estacionamento);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Estacionamento> post(@RequestBody Estacionamento obj) {
        service.create(obj);
        return ResponseEntity.ok(obj);
    }

    @Override
    @PutMapping
    public ResponseEntity<?> put(@RequestBody Estacionamento obj) {
        if(service.update(obj)) {
            return ResponseEntity.ok(obj);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(service.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    @PutMapping(value = "/saida")
    public ResponseEntity<Estacionamento> putSaida(@RequestBody Estacionamento obj) {
        service.saidaEstacionamento(obj);
        return ResponseEntity.ok().body(obj);
    }

}
