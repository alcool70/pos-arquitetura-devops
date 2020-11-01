package com.rodrigofujioka.dev.web.service.dto;

import com.rodrigofujioka.dev.web.domain.Disciplina;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class DisciplinaNomeProfessorDTOTest {

	Disciplina d;
	DisciplinaNomeProfessorDTO dto;

	@BeforeEach
	void setUp(){
		d = new Disciplina();
		d.setId(1L);
		d.setAnoDisciplina(1999);
		d.setNome("Economia");
		d.setProfessor("Godofredo");
	}

	@Test
	public void whenCreatingDTO_thenValidateItsData(){
		dto = new DisciplinaNomeProfessorDTO(d);
		assertThat(dto.getNome(), startsWith("Eco"));
		assertThat(dto.getProfessor(), endsWith("fredo"));
	}

	@Test
	public void whenCreatingDTOAndSettingParams_thenValidateItsData(){
		dto = new DisciplinaNomeProfessorDTO(d);
		dto.setNome("Computação");
		dto.setProfessor("Fujioka");
		assertThat(dto.getNome(), startsWith("Comp"));
		assertThat(dto.getProfessor(), endsWith("oka"));
	}

}