package com.rodrigofujioka.dev.web.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

class PessoaTest {

	final String nome = "Zuleica";
	private PessoaFisica pf;
	private PessoaJuridica pj;

	@BeforeEach
	public void setUp() {
		pf = new PessoaFisica();
		pf.setCpf("12345678900");

		pj = new PessoaJuridica();
		pj.setCnpj("12345678000199");
	}

	@Test
	void getId() {
		assertThat(pf.getId(), nullValue());
		assertThat(pj.getId(), nullValue());
	}

	@Test
	void setId() {
		pf.setId(2L);
		assertThat(pf.getId(), equalTo(2L));

		pj.setId(2L);
		assertThat(pj.getId(), equalTo(2L));
	}

	@Test
	void getNome() {
		pf.setNome(nome.concat("set"));
		assertThat(pf.getNome(), equalTo(nome + "set"));

		pj.setNome(nome.concat("set"));
		assertThat(pj.getNome(), equalTo(nome + "set"));
	}

	@Test
	final void setNome() {
		pf.setNome(nome);
		assertThat(pf.getNome(), equalTo(nome));

		pj.setNome(nome);
		assertThat(pj.getNome(), equalTo(nome));
	}

	@Test
	final void assertDocs() {
		assertThat(pf.getCpf().length(), equalTo(11));
		assertThat(pf.getCpf(), equalTo("12345678900"));

		assertThat(pj.getCnpj().length(), equalTo(14));
		assertThat(pj.getCnpj(), equalTo("12345678000199"));
	}
}