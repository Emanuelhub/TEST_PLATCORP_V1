package br.com.test.controllers;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClienteController {

	@GetMapping(value ="/clientes/{id}")
	public String getCliente(@PathVariable("id") Long id) {
		System.out.println((id==null || "".equals(id))?"Todos":"Cliente id:"+id);
		return (id==null || "".equals(id))?"Todos":"Cliente id:"+id;
	}

	@GetMapping(value ="/clientes")
	public String getAllClientes() {
		System.out.println("Todos");
		return "Todos";
	}
	
}
