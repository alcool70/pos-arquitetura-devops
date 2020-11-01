package com.rodrigofujioka.dev.web.service;

import com.rodrigofujioka.dev.web.domain.Disciplina;
import com.rodrigofujioka.dev.web.service.dto.DisciplinaBuscaAnoDTO;
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
					new Disciplina("matematica", "Suely", 2000),
					new Disciplina("portugues", "Franciane", 2007),
					new Disciplina("fisica", "João", 2010)
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
		entityManager.persist(disciplina);
		entityManager.flush();

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

	@Test
	void testDeleteDisciplinas() {
		disciplinas.forEach(d -> entityManager.persist(d));
		entityManager.flush();

		List<Disciplina> disciplinas1 = service.listar();
		assertThat(disciplinas1.size(), equalTo(3));
		assertThat(disciplinas1.stream().map(Disciplina::getNome).collect(Collectors.toList()),
						containsInAnyOrder("matematica", "portugues", "fisica"));

		Disciplina toDelete = disciplinas1
						.stream()
						.filter(x -> x.getNome().equals("fisica"))
						.collect(Collectors.toList())
						.get(0);

		service.deletePorId(toDelete.getId());

		disciplinas1 = service.listar();
		assertThat(disciplinas1.size(), equalTo(2));
		assertThat(disciplinas1.stream().map(Disciplina::getNome).collect(Collectors.toList()),
						allOf(
										containsInAnyOrder("matematica", "portugues"),
										not(contains("fisica"))
						)
		);
	}

	@Test
	void testSaveDisciplinas() {
		disciplinas.forEach(d -> entityManager.persist(d));
		entityManager.flush();

		Disciplina medicina = new Disciplina("medicina", "Drauzio", 1950);
		service.salvar(medicina);

		List<Disciplina> disciplinas1 = service.listar();
		assertThat(disciplinas1.size(), equalTo(4));
		assertThat(disciplinas1.stream().map(Disciplina::getNome).collect(Collectors.toList()),
						containsInAnyOrder("matematica", "portugues", "fisica", "medicina"));
	}

	@Test
	void testUpdateDisciplina() {
		disciplinas.forEach(d -> entityManager.persist(d));
		entityManager.flush();

		List<Disciplina> disciplinas1 = service.listar();
		assertThat(disciplinas1.size(), equalTo(3));
		assertThat(disciplinas1.stream().map(Disciplina::getNome).collect(Collectors.toList()),
						containsInAnyOrder("matematica", "portugues", "fisica"));

		Disciplina toUpdate = disciplinas1
						.stream()
						.filter(x -> x.getNome().equals("fisica"))
						.collect(Collectors.toList())
						.get(0);

		toUpdate.setProfessor("Fabio");
		toUpdate.setNome("historia");

		service.update(toUpdate);

		disciplinas1 = service.listar();
		assertThat(disciplinas1.size(), equalTo(3));
		assertThat(disciplinas1.stream().map(Disciplina::getNome).collect(Collectors.toList()),
						allOf(
										containsInAnyOrder("matematica", "portugues", "historia"),
										not(contains("fisica"))
						)
		);
	}

	@Test
	void testUpdateDisciplina_ButWithNoId() {
		disciplinas.forEach(d -> entityManager.persist(d));
		entityManager.flush();

		List<Disciplina> disciplinas1 = service.listar();
		assertThat(disciplinas1.size(), equalTo(3));
		assertThat(disciplinas1.stream().map(Disciplina::getNome).collect(Collectors.toList()),
						containsInAnyOrder("matematica", "portugues", "fisica"));

		Exception ex = assertThrows(RuntimeException.class, () -> {
			service.update(null);
		});

		assertThat(ex.getMessage(), equalTo("ID precisa ser informado"));
	}

	@Test
	void testGetDisciplinasEntreAnos() {
		disciplinas.forEach(d -> entityManager.persist(d));
		entityManager.flush();

		List<Disciplina> disciplinas1 = service.getDisciplinaEntreAnos(2007, 2010);
		assertThat(disciplinas1.size(), equalTo(2));
		assertThat(disciplinas1.stream().map(Disciplina::getNome).collect(Collectors.toList()),
						containsInAnyOrder("portugues", "fisica"));
	}

	@Test
	void testGetDisciplinasEntreAnosWithDTO() {
		disciplinas.forEach(d -> entityManager.persist(d));
		entityManager.flush();

		DisciplinaBuscaAnoDTO disciplinaBuscaAnoDTO =
						new DisciplinaBuscaAnoDTO(2007, 2010);

		List<Disciplina> disciplinas1 = service.getDisciplinaEntreAnos(disciplinaBuscaAnoDTO);
		assertThat(disciplinas1.size(), equalTo(2));
		assertThat(disciplinas1.stream().map(Disciplina::getNome).collect(Collectors.toList()),
						containsInAnyOrder("portugues", "fisica"));
	}

}