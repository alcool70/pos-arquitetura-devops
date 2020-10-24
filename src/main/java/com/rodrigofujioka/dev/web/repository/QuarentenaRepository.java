package com.rodrigofujioka.dev.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigofujioka.dev.web.domain.Quarentena;

@Repository
public interface QuarentenaRepository
				extends JpaRepository<Quarentena, Long> {

	//select * from quarentena where uf=
	List<Quarentena> findQuarentenaByUf(String uf);

	List<Quarentena> findQuarentenaByUfAndCidade(String uf, String cidade);

	List<Quarentena> findQuarentenaByNomePessoaAndUf(String nomePessoa, String uf);

}
