package com.rodrigofujioka.dev.web.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestEntityManager
@Transactional

class DisciplinaServiceTest {

	@Test
	void getDisciplinaByNomeAndProfessor() {
	}

	@Test
	void getDisciplinaEntreAnos() {
	}

	@Test
	void testGetDisciplinaEntreAnos() {
	}

	@Test
	void getDisciplinaPorId() {
	}

	@Test
	void salvar() {
	}

	@Test
	void consultaPorId() {
	}

	@Test
	void deletePorId() {
	}

	@Test
	void listar() {
	}

	@Test
	void update() {
	}
}