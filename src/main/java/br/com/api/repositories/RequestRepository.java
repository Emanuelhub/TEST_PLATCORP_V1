package br.com.api.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.api.entities.Request;

public interface RequestRepository extends CrudRepository<Request, Long>{

}
