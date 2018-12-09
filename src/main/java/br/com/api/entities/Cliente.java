package br.com.api.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = -6579068215540024798L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="nome",nullable=false)
	private String nome;
	
	@Column(name="idade",nullable=false)
	private Integer idade;
	
	@OneToOne(fetch =FetchType.EAGER, cascade=CascadeType.ALL)
	private Request requestCreate;

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

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Request getRequestCreate() {
		return requestCreate;
	}

	public void setRequestCreate(Request requestCreate) {
		this.requestCreate = requestCreate;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", idade=" + idade + ", requestCreate=" + requestCreate + "]";
	}
	
}
