package com.rodrigofujioka.dev.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quarentena {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nomePessoa;
	private String uf;
	private String cidade;
	private int diasQuarentena;

	public Quarentena(){}

	public Quarentena(String uf, String cidade) {
		this.uf = uf;
		this.cidade = cidade;
	}
	public Quarentena(String uf, String cidade, String nomePessoa) {
		this.uf = uf;
		this.cidade = cidade;
		this.nomePessoa = nomePessoa;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomePessoa() {
		return nomePessoa;
	}
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public int getDiasQuarentena() {
		return diasQuarentena;
	}
	public void setDiasQuarentena(int diasQuarentena) {
		this.diasQuarentena = diasQuarentena;
	}

}
