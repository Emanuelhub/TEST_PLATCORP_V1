package br.com.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.entities.Cliente;
import br.com.api.repositories.ClienteRepository;

@Service
public class ClienteServico {
	@Autowired
	ClienteRepository clienteRepo;
	
	public Cliente save(Cliente cliente) {
		return cliente;
	}
}
