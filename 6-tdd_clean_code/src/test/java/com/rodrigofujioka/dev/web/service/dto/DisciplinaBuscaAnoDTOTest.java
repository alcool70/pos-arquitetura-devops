package com.rodrigofujioka.dev.web.service.dto;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class DisciplinaBuscaAnoDTOTest {

	DisciplinaBuscaAnoDTO d;

	@Test
	public void whenCreatingDTO_thenValidateItsData(){
		d = new DisciplinaBuscaAnoDTO(1999, 2020);
		assertThat(d.getAnoInicial(), equalTo(1999));
		assertThat(d.getAnoFinal(), greaterThan(2000));
	}

	@Test
	public void whenCreatingDTOAndDefineParamsViaSetters_thenValidateItsData(){
		d = new DisciplinaBuscaAnoDTO(1999, 2020);
		d.setAnoInicial(2010);
		d.setAnoFinal(2015);
		assertThat(d.getAnoInicial(), greaterThan(1999));
		assertThat(d.getAnoFinal(), lessThan(2020));
	}
}