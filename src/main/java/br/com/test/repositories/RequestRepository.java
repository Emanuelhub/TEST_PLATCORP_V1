package br.com.test.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.test.entities.Request;

public interface RequestRepository extends CrudRepository<Request, Long>{

}
