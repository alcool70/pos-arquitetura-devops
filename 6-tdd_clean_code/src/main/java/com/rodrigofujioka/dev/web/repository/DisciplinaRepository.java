package com.rodrigofujioka.dev.web.repository;

import com.rodrigofujioka.dev.web.domain.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

	List<Disciplina> findDisciplinaByProfessor(String professor);
	
	List<Disciplina> findDisciplinaByNomeAndProfessor(String nome, String professor);
	
	List<Disciplina> findDisciplinaByAnoDisciplinaBetween(int anoInicial, int anoFinal);
	
	List<Disciplina> findByAnoDisciplinaBetween(int anoInicial, int anoFinal);
	
}
