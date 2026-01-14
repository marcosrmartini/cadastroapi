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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/cliente")
public class ClienteController {

	//private static final Logger log = Logger.getLogger(ClienteController.class);

	@Autowired
	private ClienteService service;

	@PostMapping
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
	private List<Cliente> findAll(){
		log.info("Iniciando busca total de registros");
		return service.findAll();
	}

	@RequestMapping(value = "/porNome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> pesquisaPorNome(@PathVariable("nome") String nome) {
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
	public ResponseEntity<Cliente> pesquisaPorId(@PathVariable("id") Long id) {
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
	public ResponseEntity<Cliente> removerCliente(@PathVariable("id") Long id) {
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
