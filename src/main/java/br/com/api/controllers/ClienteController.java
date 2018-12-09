package br.com.api.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.entities.Cliente;
import br.com.api.entities.Request;
import br.com.api.exceptions.ClienteNotFoundException;
import br.com.api.repositories.ClienteRepository;
import br.com.api.services.ClienteServico;

@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepo;
	@Autowired
	ClienteServico clienteServico;
	
	@GetMapping(value ="/clientes/{id}")
	@ResponseBody
	public Cliente getOne(@PathVariable("id") Long id) {
		return clienteRepo.findById(id)
				.orElseThrow(() -> new ClienteNotFoundException(id));	
	
	}

	@GetMapping(value ="/clientes")
	@ResponseBody
	public Iterable<Cliente> getAll() {
		System.out.println("Todos");
		return clienteRepo.findAll();
	}
	
	@PostMapping("/clientes")
	@ResponseBody
	public Cliente novoCliente(@RequestBody Cliente novoCliente, HttpServletRequest request) {
		Request req = new Request();
		req.setIpOrigem(request.getRemoteAddr());
		novoCliente.setRequestCreate(req);
		return clienteRepo.save(novoCliente);
	}
	
	@PutMapping("/clientes/{id}")
	Cliente replaceEmployee(@RequestBody Cliente newCliente, @PathVariable Long id) {

		return clienteRepo.findById(id)
			.map(cliente -> {
				cliente.setNome(newCliente.getNome());
				cliente.setIdade(newCliente.getIdade());
				return clienteRepo.save(cliente);
			})
			.orElseGet(() -> {
				newCliente.setId(id);
				return clienteRepo.save(newCliente);
			});
	}

	@DeleteMapping("/clientes/{id}")
	void delete(@PathVariable Long id) {
		//clienteRepo.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
		clienteRepo.deleteById(id);
	}
}
