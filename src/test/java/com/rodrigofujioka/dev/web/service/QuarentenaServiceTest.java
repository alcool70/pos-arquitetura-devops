package com.rodrigofujioka.dev.web.service;

import com.rodrigofujioka.dev.web.domain.Quarentena;
import com.rodrigofujioka.dev.web.service.dto.QuarentenaNomeCidadeDTO;
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
public class QuarentenaServiceTest {

	final List<Quarentena> quarentenas = Arrays.asList(
					new Quarentena("PB", "João Pessoa"),
					new Quarentena("PE", "Recife"),
					new Quarentena("RN", "Natal")
	);

	@Autowired
	QuarentenaService service;

	@Autowired
	private EntityManager entityManager;

	@Test
	void testThrowExceptionWhenNoObjReturned() {
		assertThrows(NotFoundException.class, () -> {
			Quarentena q = service.getQuarentenaById(1L);
		});
	}

	@Test
	void testQuarentenaWhenObjReturned() throws NotFoundException {
		Quarentena quarentena = quarentenas
						.stream()
						.filter(x -> x.getCidade().equalsIgnoreCase("recife"))
						.collect(Collectors.toList())
						.get(0);
		entityManager.persist(quarentena);
		entityManager.flush();

		Quarentena q = service.getQuarentenaById(quarentena.getId());
		assertThat(q.getClass(), typeCompatibleWith(Quarentena.class));
	}

	@Test
	void testThrowExceptionWhenNoDTOReturned() {
		assertThrows(NotFoundException.class, () -> {
			QuarentenaNomeCidadeDTO q = service.getQuarentenaNomeCidadeById(1L);
		});
	}

	@Test
	void testDTOWhenReturned() throws NotFoundException {
		Quarentena quarentena = quarentenas
						.stream()
						.filter(x -> x.getCidade().equalsIgnoreCase("recife"))
						.collect(Collectors.toList())
						.get(0);
		entityManager.persist(quarentena);
		entityManager.flush();

		QuarentenaNomeCidadeDTO dto = service.getQuarentenaNomeCidadeById(quarentena.getId());
		assertThat(dto.getClass(), typeCompatibleWith(QuarentenaNomeCidadeDTO.class));
	}

	@Test
	void testDeleteQuarentena() throws NotFoundException {
		quarentenas.forEach(q -> entityManager.persist(q));
		entityManager.flush();

		Quarentena toDelete = service.getQuarentenaByUf("PE").get(0);
		service.deleteQuarentenaById(toDelete.getId());

		List<Quarentena> quarentenaList = service.getQuarentena();

		assertThat(
						quarentenaList
										.stream().map(Quarentena::getUf)
										.collect(Collectors.toList()),
						not(contains("PE")));
	}

	@Test
	void testThrowException_whenDeleteQuarentena() throws NotFoundException {
		Exception ex = assertThrows(NotFoundException.class, () -> {
			service.deleteQuarentenaById(1000L);
		});

		assertThat(ex.getMessage(), equalTo("Quarentena não localizada"));
	}

	@Test
	void testSaveQuarentena() {
		Quarentena quarentena = new Quarentena("SP", "São Paulo");

		service.salvar(quarentena);
		Quarentena q = service.getQuarentenaByUf("SP").get(0);
		assertThat(q.getCidade(), equalTo("São Paulo"));
	}

	@Test
	void testThrowException_whenSaveQuarentena() {
		Quarentena quarentena = quarentenas
						.stream()
						.filter(x -> x.getUf().equalsIgnoreCase("PB"))
						.collect(Collectors.toList())
						.get(0);

		Exception ex = assertThrows(
						RuntimeException.class,
						() -> service.salvar(quarentena)
		);

		assertThat(ex.getMessage(), equalToIgnoringCase("Estado não possui teste"));
	}
}
