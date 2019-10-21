package br.com.mmartini.cadastro.service;

import java.util.List;
import java.util.Optional;

import br.com.mmartini.cadastro.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mmartini.cadastro.model.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	public ClienteRepository repository;

	@Override
	public List<Cliente> pesquisaPorNome(String nome) {
		return repository.pesquisaPorNome("%" + nome + "%");
	}

	@Override
	public Optional<Cliente> pesquisaPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Cliente incluir(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	public Cliente alterar(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	public Cliente removerCliente(Long id) {
		Optional<Cliente> c = repository.findById(id);
		if (c.isPresent()) {
			repository.delete(c.get());
			return c.get();
		} else
			return new Cliente();
	}

}
