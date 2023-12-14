package com.generation.lojagames.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")

public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 64)
	@Size(min = 10, max = 64, message = "O nome do jogo precisa ter de 10 a 64 caracteres.")
	@NotBlank(message = " O nome do jogo é obrigatório.")
	private String nome;

	@Column(length = 255)
	@Size(min = 25, max = 255, message = "A descrição precisa ter de 10 a 255 caracteres.")
	@NotBlank(message = " A descrição do jogo é obrigatória.")
	private String descricao;

	@Column(length = 50)
	@Size(min = 2, max = 50, message = "O atributo Console precisa ter de 2 a 50 caracteres.")
	@NotBlank(message = "É obrigatório especificar o console no qual o jogo funciona.")
	private String console;

	@Column(length = 500)
	@Size(min = 25, max = 500, message = "O Atributo imagem precisa ter de 25 a 500 caracteres")
	@NotBlank(message = "É necessário a inclusão da imagem ilustrativa do jogo!")
	private String imagem;

	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 3, fraction = 2)
	private BigDecimal preco;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataLancamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

}