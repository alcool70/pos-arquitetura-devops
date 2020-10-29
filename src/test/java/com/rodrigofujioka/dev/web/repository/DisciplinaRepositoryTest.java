package com.rodrigofujioka.dev.web.repository;

import com.rodrigofujioka.dev.web.domain.Disciplina;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestEntityManager
@Transactional
public class DisciplinaRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

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
		assertThat(found.getNome(), equalTo(portugues.getNome()));
	}

	@Test
	public void whenFindByProfessorMila_thenReturnDisciplina() {
		// given
		Disciplina ginastica1 = new Disciplina("Bases Fisiologicas", "Mila");
		Disciplina ginastica2 = new Disciplina("Educaçao Fisica", "Mila");
		Disciplina anatomia = new Disciplina("Anatomia Humana", "Mila");

		entityManager.persist(ginastica1);
		entityManager.persist(ginastica2);
		entityManager.persist(anatomia);
		entityManager.flush();

		// when
		List<Disciplina> disciplinas =
				disciplinaRepository
						.findDisciplinaByProfessor("Mila");

		Disciplina found =
				disciplinas.stream()
						.filter(dis -> dis.getProfessor().equalsIgnoreCase("Mila"))
						.collect(Collectors.toList())
						.get(0);//uma duvida aqui... e para poder validar todas as disciplimas?

		// then
		assertThat(found.getNome(), equalTo(ginastica1.getNome()));
	}

	@Test
	public void whenFindByNomeAndProfessor_thenReturnDisciplina() {
		// given
		Disciplina matematica1 = new Disciplina("geometria", "Suely");
		Disciplina matematica2 = new Disciplina("trigonometria", "Andrea");
		entityManager.persist(matematica1);
		entityManager.persist(matematica2);
		entityManager.flush();

		// when
		List<Disciplina> disciplinas =
						disciplinaRepository
										.findDisciplinaByNomeAndProfessor("geometria","Suely");

		Disciplina found =
						disciplinas.stream()
										.filter(d -> d.getProfessor().equalsIgnoreCase("Suely"))
										.collect(Collectors.toList())
										.get(0);

		// then
		assertThat(found.getNome(), equalTo(matematica1.getNome()));
	}

}