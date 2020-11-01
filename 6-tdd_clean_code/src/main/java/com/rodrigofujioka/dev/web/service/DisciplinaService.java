package com.rodrigofujioka.dev.web.service;

import com.rodrigofujioka.dev.web.domain.Disciplina;
import com.rodrigofujioka.dev.web.repository.DisciplinaRepository;
import com.rodrigofujioka.dev.web.service.dto.DisciplinaBuscaAnoDTO;
import com.rodrigofujioka.dev.web.service.dto.DisciplinaNomeProfessorDTO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository disciplinaRepository;


	public List<Disciplina> getDisciplinaByNomeAndProfessor(String nome, String professor) {
		return disciplinaRepository.findDisciplinaByNomeAndProfessor(nome, professor);
	}

	public List<Disciplina> getDisciplinaEntreAnos(DisciplinaBuscaAnoDTO dto) {
		return disciplinaRepository
						.findDisciplinaByAnoDisciplinaBetween(
										dto.getAnoInicial(), dto.getAnoFinal());
	}

	public List<Disciplina> getDisciplinaEntreAnos(int anoInicial, int anoFinal) {
		return disciplinaRepository
						.findDisciplinaByAnoDisciplinaBetween(anoInicial, anoFinal);
	}

	public DisciplinaNomeProfessorDTO getDisciplinaPorId(Long id) throws NotFoundException {
		Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
		if (disciplina.isEmpty())
			throw new NotFoundException("Disciplina não localizada");
		else
			return new DisciplinaNomeProfessorDTO(disciplina.orElse(new Disciplina()));
	}

	public Disciplina salvar(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}

	public Disciplina consultaPorId(Long id) throws NotFoundException {
		Optional<Disciplina> disciplina = disciplinaRepository.findById(id);

		if (disciplina.isEmpty())
			throw new NotFoundException("Disciplina não localizada");
		else
			return disciplina.orElse(new Disciplina());
	}

	public void deletePorId(Long id) {
		disciplinaRepository.deleteById(id);
	}

	public List<Disciplina> listar() {
		return disciplinaRepository.findAll();
	}

	public Disciplina update(Disciplina disciplina) {
		if (disciplina != null) {
			return disciplinaRepository.save(disciplina);
		}
		throw new RuntimeException("ID precisa ser informado");
	}

}
