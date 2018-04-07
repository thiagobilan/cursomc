package com.bilangieri.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilangieri.cursomc.domain.Cliente;
import com.bilangieri.cursomc.repositories.ClienteRepository;
import com.bilangieri.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente nao Encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}

}