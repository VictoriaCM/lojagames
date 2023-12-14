package com.generation.lojagames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

import com.generation.lojagames.model.Produto;
import com.generation.lojagames.repository.ProdutoRepository;
import com.generation.lojagames.repository.CategoriaRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	// Listar todos
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());

	}

	// get by id
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

	// get by nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));

	}

	// update
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));

	}

	// Criar produto
	@PutMapping
	public ResponseEntity<Produto> update(@Valid @RequestBody Produto produto) {

		if (produtoRepository.existsById(produto.getId())) {
			if (categoriaRepository.existsById(produto.getId())) 
				if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
			
				else

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente!", null);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	// Delete by id
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		// Checa se o Id existe antes da exclusão
		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		produtoRepository.deleteById(id);

	}

}
