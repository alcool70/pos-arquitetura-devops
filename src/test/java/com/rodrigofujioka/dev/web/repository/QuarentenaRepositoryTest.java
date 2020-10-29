package com.rodrigofujioka.dev.web.repository;

import com.rodrigofujioka.dev.web.domain.Quarentena;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestEntityManager
@Transactional
public class QuarentenaRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private QuarentenaRepository quarentenaRepository;

	@Test
	public void whenFindByUF_thenReturnQuarentenaPB() {
		// given
		Quarentena quarentena = new Quarentena();
		quarentena.setUf("PB");
		quarentena.setNomePessoa("Adamastor");

		entityManager.persist(quarentena);
		entityManager.flush();

		// when
		Quarentena found =
						quarentenaRepository.findQuarentenaByUf("PB").get(0);

		// then
		assertThat(found.getUf(), equalTo(quarentena.getUf()));
	}

	@Test
	public void whenFindByCidade_thenReturnQuatroQuarentenas() {

		String cidade = "Santa Helena";
		// given
		Quarentena quarentena1 = new Quarentena("PB", cidade);
		Quarentena quarentena2 = new Quarentena("SC", cidade);
		Quarentena quarentena3 = new Quarentena("PR", cidade);
		Quarentena quarentena4 = new Quarentena("MA", cidade);
		Quarentena quarentena5 = new Quarentena("MG", "Divinópolis");

		entityManager.persist(quarentena1);
		entityManager.persist(quarentena2);
		entityManager.persist(quarentena3);
		entityManager.persist(quarentena4);
		entityManager.persist(quarentena5);
		entityManager.flush();

		// when
		List<Quarentena> quarentenas =
						quarentenaRepository
										.findQuarentenaByCidade(cidade);

		// then
		assertThat(quarentenas.size(), equalTo(4));
		assertThat(quarentenas.stream().map(Quarentena::getUf).collect(Collectors.toList()),
						containsInAnyOrder("MA","PB", "SC","PR"));
	}

}