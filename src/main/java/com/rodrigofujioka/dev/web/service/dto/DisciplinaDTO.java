package com.rodrigofujioka.dev.web.service.dto;

public class DisciplinaDTO {
	private Long id;
	private String nome;
	private String professor;
//	private int anoDisciplina;

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getNome() { return nome; }

	public void setNome(String nome) { this.nome = nome; }

	public String getProfessor() { return professor; }

	public void setProfessor(String professor) { this.professor = professor; }

//	public int getAnoDisciplina() { return anoDisciplina; }

//	public void setAnoDisciplina(int anoDisciplina) {
//		this.anoDisciplina = anoDisciplina; }
}
