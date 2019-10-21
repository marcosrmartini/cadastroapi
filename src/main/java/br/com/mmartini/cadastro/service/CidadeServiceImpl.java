package br.com.mmartini.cadastro.service;

import java.util.List;

import br.com.mmartini.cadastro.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mmartini.cadastro.model.Cidade;

@Service
public class CidadeServiceImpl implements CidadeService{
	
	@Autowired
	public CidadeRepository repository;

	@Override
	public Cidade incluir(Cidade cidade) {	
		return repository.save(cidade);
	}

	@Override
	public List<Cidade> pesquisaPorNome(String nome) {		
		return repository.pesquisaPorNome(nome);
	}

	@Override
	public List<Cidade> pesquisaPorEstado(String estado) {		
		return repository.pesquisaPorEstado(estado);
	}

	
	
}
