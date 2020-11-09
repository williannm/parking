package com.coffee.parking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffee.parking.exception.RegraException;
import com.coffee.parking.models.Veiculo;
import com.coffee.parking.repository.VeiculoRepository;


@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	public List<Veiculo> listar() {
		return veiculoRepository.findAll();
	}

	public Optional<Veiculo> buscar(Long id) {
		return veiculoRepository.findById(id);
	}

	public Optional<Veiculo> buscarPorPlaca(String placa) {
		return veiculoRepository.findByPlaca(placa);
	}

	public Veiculo salvar(Veiculo veiculo) throws RegraException{
		if (contem(veiculo.getPlaca())) {
			throw new RegraException("Placa já existe no sistema");
		}
		return veiculoRepository.save(veiculo);
	}	

	public void apagar(Long id) {
		veiculoRepository.deleteById(id);
	}

	public boolean contem(Long id) {
		return veiculoRepository.existsById(id);
	}
	
	public boolean contem(String placa) {
		return veiculoRepository.findByPlaca(placa).isPresent();
	}
}