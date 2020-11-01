package com.rodrigofujioka.dev.web.service.dto;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class DisciplinaDTOTest {

	DisciplinaDTO d;

	@Test
	public void whenCreatingDTO_thenValidateItsData(){
		d = new DisciplinaDTO();
		d.setAnoDisciplina(2010);
		d.setId(1L);
		d.setNome("Geologia");
		d.setProfessor("Alfredo");

		assertThat(d.getAnoDisciplina(), allOf(greaterThan(2000), lessThan(2020)));
		assertThat(d.getId(), lessThan(10L));
		assertThat(d.getNome(), startsWith("Geo"));
		assertThat(d.getProfessor(), endsWith("fredo"));
	}
}