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
	public void whenFindByUfAndCidade_thenReturnQuarentenaPE() {
		//findQuarentenaByUfAndCidade
		String uf = "PE";
		String cidade = "Recife";
		// given
		Quarentena quarentena = new Quarentena();
		quarentena.setUf(uf);
		quarentena.setCidade(cidade);

		entityManager.persist(quarentena);
		entityManager.flush();

		// when
		Quarentena found =
				quarentenaRepository.findQuarentenaByUfAndCidade(uf,cidade).get(0);

		// then
		assertThat(found.getUf(), equalTo(quarentena.getUf()));
	}

	@Test
	public void whenFindByNomePessoaAndUf_thenReturnUmaQuarentenaPRBozena() {
		//findQuarentenaByNomePessoaAndUf
		String cidade = "Pato Branco";

		// given
		Quarentena quarentena1 = new Quarentena("PR", cidade, "Bozena");
		Quarentena quarentena2 = new Quarentena("PE", cidade, "Bozena");
		Quarentena quarentena3 = new Quarentena("PR", cidade);
		Quarentena quarentena4 = new Quarentena("RN", cidade);
		Quarentena quarentena5 = new Quarentena("SP", "Sao Paulo");

		entityManager.persist(quarentena1);
		entityManager.persist(quarentena2);
		entityManager.persist(quarentena3);
		entityManager.persist(quarentena4);
		entityManager.persist(quarentena5);
		entityManager.flush();

		// when
		List<Quarentena> quarentenas =
				quarentenaRepository
						.findQuarentenaByNomePessoaAndUf("Bozena","PR");

		// then
		assertThat(quarentenas.size(), equalTo(1));
		assertThat(quarentenas.stream().map(x -> x.getUf()).collect(Collectors.toList()),
				containsInAnyOrder("PR"));
		assertThat(quarentenas.stream().map(x -> x.getNomePessoa()).collect(Collectors.toList()),
				containsInAnyOrder("Bozena"));
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