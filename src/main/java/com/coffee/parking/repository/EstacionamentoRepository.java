package com.coffee.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffee.parking.models.Cliente;
import com.coffee.parking.models.Estacionamento;
import com.coffee.parking.models.Veiculo;


@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long>{
	
	List<Estacionamento> findByCliente(Cliente cliente);
	List<Estacionamento> findByVeiculo(Veiculo veiculo);
}