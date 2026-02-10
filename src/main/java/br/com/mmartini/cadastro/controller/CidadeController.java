package br.com.mmartini.cadastro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mmartini.cadastro.model.Cidade;
import br.com.mmartini.cadastro.service.CidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/cidade")
@Tag(name = "Cidade", description = "Endpoints para gerenciamento de cidades")
public class CidadeController {

	@Autowired
	private CidadeService service;
	
	@RequestMapping(value = "/porCidade/{cidade}", method = RequestMethod.GET)
	@Operation(summary = "Buscar cidades por nome", description = "Busca cidades que contenham o nome especificado")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cidades encontradas"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public List<Cidade> pesquisaPorCidade(
			@Parameter(description = "Nome da cidade a ser pesquisada")
			@PathVariable("cidade") String cidade){
		log.info("Iniciando pesquisa por cidade");
		return service.pesquisaPorNome(cidade);
	}
	
	@RequestMapping(value = "/porEstado/{estado}", method = RequestMethod.GET)
	@Operation(summary = "Buscar cidades por estado", description = "Busca cidades que pertencem ao estado especificado")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cidades encontradas"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public List<Cidade> pesquisaPorEstado(
			@Parameter(description = "Estado (UF) a ser pesquisado")
			@PathVariable("estado") String estado){
		log.info("Iniciando pesquisa por estado");
		return service.pesquisaPorEstado(estado);
	}
	
	@PostMapping
	@Operation(summary = "Criar nova cidade", description = "Cria uma nova cidade no sistema")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cidade criada com sucesso"),
		@ApiResponse(responseCode = "400", description = "Requisição inválida"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public Cidade incluiCidade(@Valid @RequestBody Cidade cidade) {
		log.info("Criando nova cidade: {}", cidade.getNome());
		return service.incluir(cidade);
	}

}
