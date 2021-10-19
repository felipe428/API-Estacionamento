package com.estacionamento;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estacionamento.model.Carro;
import com.estacionamento.model.Cliente;
import com.estacionamento.repository.CarroRepository;
import com.estacionamento.repository.ClienteRepository;

@SpringBootApplication
public class EstacionamentoApplication implements CommandLineRunner{

	@Autowired
	private ClienteRepository clirepository;
	
	@Autowired
	private CarroRepository carrepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EstacionamentoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cliente c1 = new Cliente("Manoel", "14968523708", "13985049607");
		Cliente c2 = new Cliente("Rene", "85269740397", "19632574089");
		Cliente c3 = new Cliente("Felipe", "85697412391", "21394856070");
		clirepository.saveAll(Arrays.asList(c1, c2, c3));
		Carro car1 = new Carro("VW","TCroos","preto","abr1596",c1);
		Carro car2 = new Carro("Ford","Ranger","branco","aht1985",c2);
		Carro car3 = new Carro("Nissan","GTR","azul","ufe8620",c3);
		carrepository.saveAll(Arrays.asList(car1, car2, car3));
	}
}
