package com.coffee.parking.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffee.parking.exception.RegraException;
import com.coffee.parking.models.Cliente;
import com.coffee.parking.repository.ClienteRepository;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	public List<Cliente> listarNome(String nome) {
		return clienteRepository.findByNomeContaining(nome);
	}
	
	public Optional<Cliente> buscar(Long id) {
		return clienteRepository.findById(id);
	}
	
	public Optional<Cliente> buscarCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}

	public Cliente salvar(Cliente cliente) throws RegraException {
		if (contem(cliente.getCpf())) {
			throw new RegraException("CPF já existe no sistema");
		}
		return clienteRepository.save(cliente);
	}

	public boolean contem(String cpf) {
		return clienteRepository.findByCpf(cpf).isPresent();
	}
	
	public boolean contem(Long id) {
		return clienteRepository.findById(id).isPresent();
	}
	
	public void apagar(Long id) {
		clienteRepository.deleteById(id);
	}
	
}
