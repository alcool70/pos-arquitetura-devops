package com.rodrigofujioka.dev.web.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class QuarentenaTest {

	@Test
	void whenCreateQuarentenaWithId1_thenGetQuarentenaWithId1() {
		Quarentena q = new Quarentena();

		q.setCidade("Sousa");
		q.setDiasQuarentena(10);
		q.setId(1L);
		q.setNomePessoa("José");
		q.setUf("PB");


		assertThat(q.getClass(), typeCompatibleWith(Quarentena.class));
		assertThat(q.getId(), equalTo(1L));
		assertThat(q.getNomePessoa(), startsWith("Jos"));
		assertThat(q.getCidade(), equalTo("Sousa"));
		assertThat(q.getUf(), anyOf(equalTo("PB"), equalTo("PE")));
		assertThat(q.getDiasQuarentena(), instanceOf(Integer.class));
	}

}