package br.com.mmartini.cadastro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mmartini.cadastro.model.Cliente;
import br.com.mmartini.cadastro.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/cliente")
@Tag(name = "Cliente", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@PostMapping
	@Operation(summary = "Criar novo cliente", description = "Cria um novo cliente no sistema")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente criado com sucesso"),
		@ApiResponse(responseCode = "202", description = "Requisição aceita"),
		@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public ResponseEntity<Cliente> incluirCliente(@Valid @RequestBody Cliente cliente) {
		Cliente response = new Cliente();
		try {
			response = service.incluir(cliente);
			if (response.getId() == null || response.getId() == 0L) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			} else {
				return ResponseEntity.ok().body(response);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping
	@Operation(summary = "Atualizar cliente", description = "Atualiza um cliente existente no sistema")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Cliente atualizado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public ResponseEntity<Cliente> alterarCliente(@Valid @RequestBody Cliente cliente) {
		Cliente response = new Cliente();
		try {
			response = service.alterar(cliente);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	@Operation(summary = "Listar todos os clientes", description = "Retorna uma lista de todos os clientes cadastrados")
	@ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
	public List<Cliente> findAll(){
		log.info("Iniciando busca total de registros");
		return service.findAll();
	}

	@RequestMapping(value = "/porNome/{nome}", method = RequestMethod.GET)
	@Operation(summary = "Buscar clientes por nome", description = "Busca clientes que contenham o nome especificado")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Clientes encontrados"),
		@ApiResponse(responseCode = "202", description = "Nenhum cliente encontrado"),
		@ApiResponse(responseCode = "404", description = "Não encontrado"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public ResponseEntity<List<Cliente>> pesquisaPorNome(
			@Parameter(description = "Nome do cliente a ser pesquisado")
			@PathVariable("nome") String nome) {
		log.info("Iniciando pesquisa por nome");
		List<Cliente> response = new ArrayList();
		try {
			response = service.pesquisaPorNome(nome);
			if (response.isEmpty()) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			} else {
				return ResponseEntity.ok().body(response);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping(value = "/porId/{id}")
	@Operation(summary = "Buscar cliente por ID", description = "Retorna um cliente específico pelo seu ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente encontrado"),
		@ApiResponse(responseCode = "204", description = "Cliente não encontrado"),
		@ApiResponse(responseCode = "404", description = "Não encontrado"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public ResponseEntity<Cliente> pesquisaPorId(
			@Parameter(description = "ID do cliente a ser pesquisado")
			@PathVariable("id") Long id) {
		Optional<Cliente> response;
		try {
			response = service.pesquisaPorId(id);
			if (!response.isPresent()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				return ResponseEntity.ok().body(response.get());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar cliente", description = "Remove um cliente do sistema")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public ResponseEntity<Cliente> removerCliente(
			@Parameter(description = "ID do cliente a ser removido")
			@PathVariable("id") Long id) {
		Cliente response = new Cliente();
		try {
			response = service.removerCliente(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.notFound().build();
	}

}
