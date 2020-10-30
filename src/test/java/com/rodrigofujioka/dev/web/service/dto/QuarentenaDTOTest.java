package com.rodrigofujioka.dev.web.service.dto;

import com.rodrigofujioka.dev.web.domain.Produto;
import com.rodrigofujioka.dev.web.domain.Quarentena;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class QuarentenaDTOTest {

	Quarentena q;

	@BeforeEach
	void setUp(){
		q = new Quarentena();
		q.setId(1L);
		q.setCidade("San Francisco");
		q.setDiasQuarentena(120);
		q.setNomePessoa("Francis");
		q.setUf("EX");
	}

	@Test
	void whenCreatingDTO_thenValidateItsData(){
		QuarentenaDTO dto = new QuarentenaDTO();
		dto.setCidade(q.getCidade());
		dto.setDiasQuarentena(q.getDiasQuarentena());
		dto.setId(q.getId());
		dto.setNomePessoa(q.getNomePessoa());
		dto.setUf(q.getUf());

		assertThat(dto.getClass(), typeCompatibleWith(QuarentenaDTO.class));
		assertThat(dto.getId(), equalTo(1L));
		assertThat(dto.getCidade(), equalTo(q.getCidade()));
		assertThat(dto.getDiasQuarentena(), equalTo(q.getDiasQuarentena()));
		assertThat(dto.getNomePessoa(), equalTo(q.getNomePessoa()));
		assertThat(dto.getUf(), equalTo(q.getUf()));
	}

}