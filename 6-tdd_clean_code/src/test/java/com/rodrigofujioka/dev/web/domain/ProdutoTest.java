package com.rodrigofujioka.dev.web.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ProdutoTest {

	@Test
	void whenCreateProductWithId1_thenGetProductWithId1() {
		Produto p = new Produto();
		p.setId(1L);
		p.setAnoFabricacao(1999);
		p.setNome("PC");
		p.setMarca("HP");
		p.setCodigoBarras("78900000000001");
		p.setDataCriacao(LocalDate.of(p.getAnoFabricacao(), Month.JANUARY, 27));
		p.setDataVencimento(LocalDate.of(p.getAnoFabricacao() + 1, Month.JANUARY,
						27));
		p.setLocalFabricacao("PB");

		assertThat(p.getClass(), typeCompatibleWith(Produto.class));
		assertThat(p.getId(), equalTo(1L));
		assertThat(p.getNome(), equalTo("PC"));
		assertThat(p.getMarca(), equalTo("HP"));
		assertThat(p.getAnoFabricacao(), equalTo(1999));
		assertThat(p.getCodigoBarras(), startsWith("789"));
		assertThat(p.getDataCriacao(), instanceOf(LocalDate.class));
		assertThat(p.getDataVencimento(), instanceOf(LocalDate.class));
		assertThat(p.getLocalFabricacao(), allOf(equalTo("PB"),
						instanceOf(String.class)));
	}

}