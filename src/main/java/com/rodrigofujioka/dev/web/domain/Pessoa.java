package com.rodrigofujioka.dev.web.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pessoa", discriminatorType = DiscriminatorType.INTEGER)
public class Pessoa {

	@Id
	private Long id;
	private String nome;
	private String telefone;
	private String cidade;
	private String uf;
	private String email;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

}
