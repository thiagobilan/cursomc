package com.bilangieri.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilangieri.cursomc.domain.Pedido;
import com.bilangieri.cursomc.repositories.PedidoRepository;
import com.bilangieri.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	
	public Pedido buscar(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido nao Encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		
	}

}