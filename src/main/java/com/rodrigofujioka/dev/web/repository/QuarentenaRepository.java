package com.rodrigofujioka.dev.web.repository;

import com.rodrigofujioka.dev.web.domain.Quarentena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuarentenaRepository extends JpaRepository<Quarentena, Long> {

	List<Quarentena> findQuarentenaByUf(String uf);

	List<Quarentena> findQuarentenaByUfAndCidade(String uf, String cidade);

	List<Quarentena> findQuarentenaByCidade(String cidade);

	List<Quarentena> findQuarentenaByNomePessoaAndUf(String nomePessoa, String uf);

}
