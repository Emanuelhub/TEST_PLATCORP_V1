package br.com.api.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.api.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{

}
