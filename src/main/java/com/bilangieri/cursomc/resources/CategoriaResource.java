package com.bilangieri.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	private int id;
	private String nome;
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar() {
		return "REST esta funcionando"; 
	}

}
