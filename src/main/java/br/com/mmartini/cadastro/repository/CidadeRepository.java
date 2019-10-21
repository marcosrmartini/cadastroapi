package br.com.mmartini.cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mmartini.cadastro.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Query("select c from Cidade c where c.nome = :nome")
	public List<Cidade> pesquisaPorNome(@Param("nome") String nome);
	
	@Query("select c from Cidade c where c.estado = :estado")
	public List<Cidade> pesquisaPorEstado(@Param("estado") String estado);

}
