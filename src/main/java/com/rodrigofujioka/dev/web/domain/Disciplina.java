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

	public Disciplina() {}

	public Disciplina(String nome, String professor) {
		this.nome = nome;
		this.professor = professor;
	}
	public Disciplina(String nome, String professor, int ano) {
		this.nome = nome;
		this.professor = professor;
		this.anoDisciplina = ano;
	}

	public Disciplina(String nome, String professor, int ano) {
		this.nome = nome;
		this.professor = professor;
		this.anoDisciplina = ano;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAnoDisciplina() {
		return anoDisciplina;
	}

	public void setAnoDisciplina(int ano) { this.anoDisciplina = ano; }

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String prof) { this.professor = prof; }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
