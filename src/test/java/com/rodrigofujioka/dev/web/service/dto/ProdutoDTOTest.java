package com.rodrigofujioka.dev.web.service.dto;

import com.rodrigofujioka.dev.web.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ProdutoDTOTest {

	Produto p;

	@BeforeEach
	void setUp(){
		p = new Produto();
		p.setId(1L);
		p.setNome("Celular");
		p.setMarca("iPhone 2000");
	}

	@Test
	void whenCreatingDTO_thenValidateItsData(){
		ProdutoDTO dto = new ProdutoDTO(p);

		assertThat(dto.getClass(), typeCompatibleWith(ProdutoDTO.class));
		assertThat(dto.getId(), equalTo(1L));
		assertThat(dto.getNome(), equalTo("Celular"));
		assertThat(dto.getMarca(), startsWithIgnoringCase("iphone"));
	}

	@Test
	void whenCreatingDTOAndSettingParams_thenValidateItsData(){
		ProdutoDTO dto = new ProdutoDTO(p);

		dto.setId(2L);
		dto.setMarca("Apple");
		dto.setNome("iPhone");

		assertThat(dto.getClass(), typeCompatibleWith(ProdutoDTO.class));
		assertThat(dto.getId(), equalTo(2L));
		assertThat(dto.getNome(), startsWithIgnoringCase("iphone"));
		assertThat(dto.getMarca(), equalTo("Apple"));
	}

}