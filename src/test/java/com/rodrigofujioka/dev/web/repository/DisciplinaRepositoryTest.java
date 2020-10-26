package com.rodrigofujioka.dev.web.repository;

import com.rodrigofujioka.dev.web.domain.Disciplina;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class DisciplinaRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	public void whenFindByProfessorJoao_thenReturnDisciplinaPortugues() {
		// given
		Disciplina portugues = new Disciplina("portugues", "Joao");
		entityManager.persist(portugues);
		entityManager.flush();

		// when
		Disciplina found =
						disciplinaRepository.findDisciplinaByProfessor("Joao").get(0);

		// then
		assertThat(found.getNome(), equals(portugues.getNome()));
	}

}