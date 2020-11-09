package com.coffee.parking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffee.parking.exception.RegraException;
import com.coffee.parking.models.Cliente;
import com.coffee.parking.models.Estacionamento;
import com.coffee.parking.models.Veiculo;
import com.coffee.parking.repository.EstacionamentoRepository;

@Service
public class EstacionamentoService {

	@Autowired
	private EstacionamentoRepository estacionamentoRepository;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private ClienteService clienteService;

	public List<Estacionamento> listar() {
		return estacionamentoRepository.findAll();
	}

	public Optional<Estacionamento> buscar(Long id) {
		return estacionamentoRepository.findById(id);
	}
	

	public List<Estacionamento> listarCliente(Long idCliente) {
		return estacionamentoRepository.findByCliente(new Cliente(idCliente));
	}
	
	public List<Estacionamento> listarVeiculo(Long idVeiculo) {
		return estacionamentoRepository.findByVeiculo(new Veiculo(idVeiculo));
	}

	public Estacionamento salvar(Estacionamento estacionamento) throws RegraException {
	
		Veiculo veiculo = veiculoService.buscar(estacionamento.getVeiculo().getId())
			.orElseThrow(() -> new RegraException("Veiculo não encontrado no sistema"));
		
		Cliente cliente = clienteService.buscar(estacionamento.getCliente().getId())
			.orElseThrow(() -> new RegraException("Cliente não encontrado no sistema"));
		
		estacionamento.setVeiculo(veiculo);
		estacionamento.setCliente(cliente);
		estacionamento.setHoraEntrada(LocalDateTime.now());
		
		return estacionamentoRepository.save(estacionamento);
	}

	public void apagar(Long id) {
		estacionamentoRepository.deleteById(id);
	}

	public boolean contem(Long id) {
		return estacionamentoRepository.existsById(id);
	}	
}
	

