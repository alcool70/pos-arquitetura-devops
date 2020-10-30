package com.rodrigofujioka.dev.web.service;

import com.rodrigofujioka.dev.web.domain.Disciplina;
import com.rodrigofujioka.dev.web.service.dto.DisciplinaNomeProfessorDTO;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestEntityManager
@Transactional
class DisciplinaServiceTest {

	final List<Disciplina> disciplinas = Arrays.asList(
					new Disciplina("matematica", "Suely"),
					new Disciplina("portugues", "Franciane"),
					new Disciplina("fisica", "João")
	);

	@Autowired
	DisciplinaService service;

	@Autowired
	private EntityManager entityManager;

	@Test
	void testThrowExceptionWhenNoObjReturned() {
		assertThrows(NotFoundException.class, () -> {
			DisciplinaNomeProfessorDTO d = service.getDisciplinaPorId(1L);
		});
	}

	@Test
	void testDisciplinaWhenObjReturned(){
		entityManager.persist(
						disciplinas
										.stream()
										.filter(d -> d.getNome().equals("matematica"))
										.collect(Collectors.toList())
										.get(0)
		);
		entityManager.flush();

		Disciplina d = service.getDisciplinaByNomeAndProfessor(
						"matematica", "Suely").get(0);
		assertThat(d.getClass(), typeCompatibleWith(Disciplina.class));
	}

	@Test
	void testDisciplinaByIdWhenObjReturned() throws NotFoundException {
		Disciplina disciplina = disciplinas
						.stream()
						.filter(d -> d.getNome().equals("matematica"))
						.collect(Collectors.toList())
						.get(0);
//		disciplina.setId(1000L);
		entityManager.persist(disciplina);
		entityManager.flush();

		DisciplinaNomeProfessorDTO d = service.getDisciplinaPorId(disciplina.getId());
		assertThat(d.getClass(), typeCompatibleWith(DisciplinaNomeProfessorDTO.class));
		assertThat(d.getNome(), equalTo("matematica"));
		assertThat(d.getProfessor(), equalTo("Suely"));
	}

	@Test
	void testConsultaByIdWhenNoObjReturned() {
		assertThrows(NotFoundException.class, () -> {
			Disciplina d = service.consultaPorId(1L);
		});
	}

	@Test
	void testConsultaByIdWhenObjReturned() throws NotFoundException {
		Disciplina disciplina = disciplinas
						.stream()
						.filter(d -> d.getNome().equals("fisica"))
						.collect(Collectors.toList())
						.get(0);
//		disciplina.setId(2000L);
		entityManager.persist(disciplina);
		entityManager.flush();
		System.out.println(">>> " + disciplina.getId());

		Disciplina d = service.consultaPorId(disciplina.getId());
		assertThat(d.getClass(), typeCompatibleWith(Disciplina.class));
		assertThat(d.getNome(), equalTo("fisica"));
		assertThat(d.getProfessor(), equalTo("João"));
	}

	@Test
	void testListDisciplinas() {
		disciplinas.forEach(d -> entityManager.persist(d));
		entityManager.flush();

		List<Disciplina> ds = service.listar();
		assertThat(ds.size(), equalTo(3));
		assertThat(ds.stream().map(Disciplina::getNome).collect(Collectors.toList()),
						containsInAnyOrder("matematica", "portugues", "fisica"));
	}

}