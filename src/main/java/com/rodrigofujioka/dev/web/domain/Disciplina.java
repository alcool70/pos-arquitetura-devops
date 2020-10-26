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
//	private String descricao;
	private String professor;
	private int anoDisciplina;
//	private String codigoInstituicao;

	public final String getNome() { return nome; }

	public final void setNome(String nome) { this.nome = nome; }

	public final String getProfessor() { return professor; }

//	public void setProfessor(String professor) { this.professor = professor; }

	public final int getAnoDisciplina() { return anoDisciplina; }

//	public void setAnoDisciplina(int anoDisciplina) {
//		this.anoDisciplina = anoDisciplina;
//	}

//	public String getCodigoInstituicao() { return codigoInstituicao; }

//	public void setCodigoInstituicao(String codigoInstituicao) {
//		this.codigoInstituicao = codigoInstituicao;
//	}

	public final Long getId() { return id; }

	public final void setId(Long id) { this.id = id; }

//	public String getDescricao() { return descricao; }
//
//	public void setDescricao(String descricao) { this.descricao = descricao; }

}
