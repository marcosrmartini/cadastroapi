package br.com.mmartini.cadastro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import br.com.mmartini.cadastro.model.Cliente;

public interface ClienteService {

	public Cliente incluir(Cliente cliente);
	public List<Cliente> pesquisaPorNome(String nome);
	public Optional<Cliente> pesquisaPorId(Long id);
	public Cliente alterar(Cliente cliente);
	public Cliente removerCliente(Long id);
	public List<Cliente> findAll();
	
}
