package br.com.api.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.dtos.ClienteDTO;
import br.com.api.dtos.RequestDTO;
import br.com.api.entities.Cliente;
import br.com.api.exceptions.ClienteNotFoundException;
import br.com.api.repositories.ClienteRepository;
import br.com.api.services.ClienteServico;

@RestController
//@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepo;
	@Autowired
	ClienteServico clienteServico;
	
	@GetMapping(value ="/clientes/{id}")
	@ResponseBody
	@ResponseStatus(code=HttpStatus.OK)
	public Cliente getOne(@PathVariable("id") Long id) {
		return clienteRepo.findById(id)
				.orElseThrow(() -> new ClienteNotFoundException(id));	
	
	}

	@GetMapping(value ="/clientes")
	@ResponseBody
	public Iterable<Cliente> getAll() {
		return clienteRepo.findAll();
	}
	
	@ResponseBody
	@PostMapping(path="/clientes", consumes = "application/json", produces = "application/json")
	@ResponseStatus(code=HttpStatus.CREATED)
	public ClienteDTO novoCliente(@RequestBody ClienteDTO novoClienteDTO, HttpServletRequest request) {
		RequestDTO req = new RequestDTO();
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        req.setIpOrigem(remoteAddr);
        novoClienteDTO.setRequestCreate(req);
		return clienteServico.save(novoClienteDTO);
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
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	void delete(@PathVariable Long id) {
		//clienteRepo.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
		clienteRepo.deleteById(id);
	}
}
