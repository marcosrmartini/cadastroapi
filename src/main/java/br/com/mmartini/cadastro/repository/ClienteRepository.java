package br.com.mmartini.cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mmartini.cadastro.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("select c from Cliente c where upper(c.nomeCompleto) like upper(:nome)")
	public List<Cliente> pesquisaPorNome(@Param("nome") String nome);

}
