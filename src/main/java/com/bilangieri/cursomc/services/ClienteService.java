package com.bilangieri.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bilangieri.cursomc.domain.Cidade;
import com.bilangieri.cursomc.domain.Cliente;
import com.bilangieri.cursomc.domain.Endereco;
import com.bilangieri.cursomc.domain.TipoCliente;
import com.bilangieri.cursomc.dto.ClienteDTO;
import com.bilangieri.cursomc.dto.ClienteNewDTO;
import com.bilangieri.cursomc.repositories.ClienteRepository;
import com.bilangieri.cursomc.repositories.EnderecoRepository;
import com.bilangieri.cursomc.services.exceptions.DataIntegrityException;
import com.bilangieri.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {

		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente nao Encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir esse Cliente devido a pedidos");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);

	}

	public Cliente fromDTO(ClienteDTO objDTO) {

		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.geteMail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDTO) {

		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.geteMail(), objDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.seteMail(obj.geteMail());

	}

}