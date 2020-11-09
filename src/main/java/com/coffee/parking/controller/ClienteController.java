package com.coffee.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffee.parking.controller.dto.ClienteDto;
import com.coffee.parking.models.Cliente;
import com.coffee.parking.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@GetMapping
	public List<ClienteDto> lista(){
			List<Cliente> clientes = clienteRepository.findAll();
			return ClienteDto.converter(clientes);		
		}	
	
	@PostMapping("/salvar")
	public Cliente cadastraCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
}
