package br.com.test.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.test.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{

}
