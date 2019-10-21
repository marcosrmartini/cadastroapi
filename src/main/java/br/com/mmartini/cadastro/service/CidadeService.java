package br.com.mmartini.cadastro.service;

import java.util.List;

import br.com.mmartini.cadastro.model.Cidade;

public interface CidadeService {

	public Cidade incluir(Cidade cidade);
	public List<Cidade> pesquisaPorNome(String nome);
	public List<Cidade> pesquisaPorEstado(String estado);
	
}
