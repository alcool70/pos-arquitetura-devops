package com.rodrigofujioka.dev.web.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class DisciplinaTest {

	@Test
	void getNome() {
		Disciplina d = new Disciplina("Portugues", "Salomao");
		assertThat(d.getNome(), equalTo("Portugues"));
	}

	@Test
	void setNome() {
		Disciplina d = new Disciplina();
		d.setNome("Portugues");
		assertThat(d.getNome(), equalTo("Portugues"));
	}

	@Test
	void getAnoDisciplina() {
		Disciplina d = new Disciplina();
		d.setAnoDisciplina(1993);
		assertThat(d.getAnoDisciplina(), equalTo(1993));
	}

	@Test
	void getProfessor() {
		Disciplina d = new Disciplina("Portugues", "Salomao");
		d.setProfessor("Raimundo Nonato");
		assertThat(d.getProfessor(), equalTo("Raimundo Nonato"));
	}

	@Test
	void getId() {
		Disciplina d = new Disciplina("Portugues", "Salomao");
		assertThat(d.getId(), nullValue());
	}

	@Test
	void setId() {
		Disciplina d = new Disciplina("Portugues", "Salomao");
		d.setId(2L);
		assertThat(d.getId(), allOf(notNullValue(), equalTo(2L)));
	}
}