package com.rodrigofujioka.dev.web.service.dto;

import com.rodrigofujioka.dev.web.domain.Quarentena;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.typeCompatibleWith;


class QuarentenaNomeCidadeDTOTest {

	Quarentena q;
	QuarentenaNomeCidadeDTO dto;

	@BeforeEach
	void setUp() {
		q = new Quarentena();
		q.setCidade("San Francisco");
		q.setNomePessoa("Francis");

		dto = new QuarentenaNomeCidadeDTO(q);
	}

	@Test
	void whenCreatingDTO_thenValidateItsData() {
		assertThat(dto.getClass(), typeCompatibleWith(QuarentenaNomeCidadeDTO.class));
		assertThat(dto.getCidade(), equalTo(q.getCidade()));
		assertThat(dto.getNome(), equalTo(q.getNomePessoa()));
	}

	@Test
	void whenCreatingDTOAndSettingParams_thenValidateItsData() {
		assertThat(dto.getClass(), typeCompatibleWith(QuarentenaNomeCidadeDTO.class));
		assertThat(dto.getCidade(), equalTo(q.getCidade()));
		assertThat(dto.getNome(), equalTo(q.getNomePessoa()));
	}

}