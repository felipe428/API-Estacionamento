package com.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Estacionamento extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Carro carro;
	
	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Estacionamento() {
		
	}
	
}
