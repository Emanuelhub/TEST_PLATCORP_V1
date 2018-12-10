package br.com.api.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import br.com.api.services.ClienteServico;

@RestController
//@RequestMapping("/api")
public class ClienteController {
	
	//@Autowired
	//ClienteRepository clienteRepo;
	@Autowired
	ClienteServico clienteServico;
	
	@GetMapping(value ="/clientes/{id}",produces = "application/json")
	@ResponseBody
	@ResponseStatus(code=HttpStatus.OK)
	@Cacheable(value = "findById")
	public ClienteDTO getOne(@PathVariable("id") Long id) {
		return clienteServico.findById(id);
	
	}

	@GetMapping(value ="/clientes",produces = "application/json")
	@ResponseBody
	public Iterable<ClienteDTO> getAll() {
		return clienteServico.findAll();
	}
	
	@ResponseBody
	@PostMapping(path="/clientes", consumes = "application/json", produces = "application/json")
	@ResponseStatus(code=HttpStatus.CREATED)
	public ClienteDTO novo(@RequestBody ClienteDTO novoClienteDTO, HttpServletRequest request) {
		RequestDTO req = new RequestDTO();       
        req.setIpOrigem(getIp(request));
        novoClienteDTO.setRequestCreate(req);
		return clienteServico.save(novoClienteDTO);
	}
	
	@PutMapping("/clientes/{id}")
	@ResponseStatus(code=HttpStatus.OK)
	ClienteDTO update(@RequestBody ClienteDTO newCliente, @PathVariable Long id) {
		newCliente.setId(id);
		return clienteServico.save(newCliente);
	}
	

	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	void delete(@PathVariable Long id) {
		//clienteRepo.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
		clienteServico.deleteById(id);
	}
	
	private String getIp(HttpServletRequest request) {
		String remoteAddr = "";
		
		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		return remoteAddr;
	}
}
