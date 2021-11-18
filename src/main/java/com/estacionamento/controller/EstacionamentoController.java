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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController implements ControllerInterface<Estacionamento>{

	@Autowired
    EstacionamentoService service;

    @Override
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Retorna todos os carros estacionados"),
    		@ApiResponse(responseCode = "404", description = "Não encontrado"),
    		@ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    		})
    @GetMapping(produces = "application/json")
    @Operation(summary = "Retorna todos os carros estacionados")
    public ResponseEntity<List<Estacionamento>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Retorna o carro estacionado por id"),
    		@ApiResponse(responseCode = "404", description = "Não encontrado"),
    		@ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    		})
    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Retorna um carro estacionado por id")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Estacionamento _Estacionamento = service.findById(id);
        if(_Estacionamento != null) {
            return ResponseEntity.ok(_Estacionamento);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Adiciona um carro no estacionamento"),
    		@ApiResponse(responseCode = "404", description = "Não encontrado"),
    		@ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    		})
    @PostMapping(produces = "application/json")
    @Operation(summary = "Estaciona um carro no estacionamento, e retorna a hora de entrada")
    public ResponseEntity<Estacionamento> post(@RequestBody Estacionamento obj) {
        service.create(obj);
        return ResponseEntity.ok(obj);
    }

    @Override
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Atualiza as informações do carro no estacionamento"),
    		@ApiResponse(responseCode = "404", description = "Não encontrado"),
    		@ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    		})
    @PutMapping(produces = "application/json")
    @Operation(summary = "Atualiza as informações do estacionamento")
    public ResponseEntity<?> put(@RequestBody Estacionamento obj) {
        if(service.update(obj)) {
            return ResponseEntity.ok(obj);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Apaga um carro do estacionamento")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(service.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
