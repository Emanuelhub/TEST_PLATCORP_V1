package br.com.api.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import br.com.api.responses.Response;
import br.com.api.responses.ResponseLista;
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
	public ResponseEntity<Response<ClienteDTO>> getOne(@PathVariable("id") Long id) {
		Response<ClienteDTO> response = new Response<ClienteDTO>();
		response.setData(clienteServico.findById(id));
		
		return ResponseEntity.ok(response);
	
	}

	@GetMapping(value ="/clientes",produces = "application/json")
	@ResponseBody
	public ResponseEntity<ResponseLista<ClienteDTO>> getAll() {
		ResponseLista<ClienteDTO> response = new ResponseLista<ClienteDTO>();
		clienteServico.findAll().forEach(cliente->response.getData().add(cliente));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(path="/clientes", consumes = "application/json", produces = "application/json")
	//@ResponseStatus(code=HttpStatus.CREATED)
	public ResponseEntity<Response<ClienteDTO>> novo(@Valid @RequestBody ClienteDTO novoClienteDTO, BindingResult result,
			HttpServletRequest request) {
		Response<ClienteDTO> response = new Response<ClienteDTO>();
		
		//Captura dos erros
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error->response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		//Pegando o ip da requisicao
        novoClienteDTO.setRequestCreate(getIpRequest(request));
        
        response.setData(clienteServico.save(novoClienteDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<Response<ClienteDTO>> update(@Valid @RequestBody ClienteDTO newCliente, 
			BindingResult result, @PathVariable Long id) {
		Response<ClienteDTO> response = new Response<ClienteDTO>();
		
		newCliente.setId(id);
		//Captura dos erros
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error->response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(clienteServico.save(newCliente));
		
		return ResponseEntity.ok(response);
	}
	

	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	void delete(@PathVariable Long id) {
		//clienteRepo.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
		clienteServico.deleteById(id);
	}
	
	private RequestDTO getIpRequest(HttpServletRequest request) {
		RequestDTO req = new RequestDTO(); 
		String remoteAddr = "";
		
		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		req.setIpOrigem(remoteAddr);
		return req;
	}
}
