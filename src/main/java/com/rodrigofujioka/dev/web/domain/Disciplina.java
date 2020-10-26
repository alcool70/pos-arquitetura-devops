package com.rodrigofujioka.dev.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String professor;
	private int anoDisciplina;

	public final String getNome() { return nome; }

	public final void setNome(String nome) { this.nome = nome; }

	public final String getProfessor() { return professor; }

	public final int getAnoDisciplina() { return anoDisciplina; }

	public final Long getId() { return id; }

	public final void setId(Long id) { this.id = id; }
	
}
