package com.rodrigofujioka.dev.web.rest;

import com.rodrigofujioka.dev.web.domain.Disciplina;
import com.rodrigofujioka.dev.web.service.DisciplinaService;
import com.rodrigofujioka.dev.web.service.dto.DisciplinaBuscaAnoDTO;
import com.rodrigofujioka.dev.web.service.dto.DisciplinaDTO;
import com.rodrigofujioka.dev.web.service.dto.DisciplinaNomeProfessorDTO;
import javassist.NotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DisciplinaRest {

	@Autowired
	private DisciplinaService disciplinaService;

	@PostMapping("/disciplina")
	public ResponseEntity<Disciplina> salvar(
					@RequestBody @Valid DisciplinaDTO disciplinaDTO) {
		Disciplina disciplina = new Disciplina();

		disciplina.setId(disciplinaDTO.getId());
		disciplina.setNome(disciplinaDTO.getNome());
		disciplina.setProfessor(disciplinaDTO.getProfessor());
		disciplina.setAnoDisciplina(disciplinaDTO.getAnoDisciplina());
		return ResponseEntity.ok(disciplinaService.salvar(disciplina));
	}

	@GetMapping("/disciplina/busca/{anoInicial}/{anoFinal}")
	@ResponseBody
	public ResponseEntity<List<Disciplina>> buscaDisciplinaEntreAnosPath(
					@PathVariable int anoInicial, @PathVariable int anoFinal) {

		List<Disciplina> disciplinas =
						disciplinaService.getDisciplinaEntreAnos(anoInicial, anoFinal);

		return disciplinas.size() == 0 ?
						ResponseEntity.noContent().build() :
						ResponseEntity.ok(disciplinas);
	}

	@PostMapping("/disciplina/busca")
	@ResponseBody
	public ResponseEntity<List<Disciplina>> buscaDisciplinaEntreAnos(
					@RequestBody @Valid DisciplinaBuscaAnoDTO dtoBusca) {
		return ResponseEntity
						.ok(disciplinaService.getDisciplinaEntreAnos(dtoBusca));
	}

	public ResponseEntity<DisciplinaNomeProfessorDTO> getDisciplinaProfessor(
					@PathVariable Long id) {
		try {
			DisciplinaNomeProfessorDTO disciplinaNomeProfessorDTO = disciplinaService
							.getDisciplinaPorId(id);
			return ResponseEntity.ok(disciplinaNomeProfessorDTO);
		} catch (NotFoundException e) {
			LoggerFactory.getLogger(this.getClass()).error("not found");
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping("/disciplina")
	@ResponseBody
	public ResponseEntity<Disciplina> update(
					@RequestBody @Valid DisciplinaDTO disciplinaDTO) {
		Disciplina disciplina = new Disciplina();

		disciplina.setId(disciplinaDTO.getId());
		disciplina.setNome(disciplinaDTO.getNome());
		return ResponseEntity.ok(disciplinaService.update(disciplina));
	}


	@GetMapping("/disciplina/{id}")
	@ResponseBody
	public ResponseEntity<Disciplina> consultaPorId(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(disciplinaService.consultaPorId(id));
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error("not found");
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/disciplina")
	@ResponseBody
	public ResponseEntity<List<Disciplina>> listar() {
		return ResponseEntity.ok(disciplinaService.listar());
	}

	@DeleteMapping("/disciplina/{id}")
	public ResponseEntity<Disciplina> deletePorId(@PathVariable Long id) {
		try {
			disciplinaService.deletePorId(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error("bad request");
			return ResponseEntity.badRequest().build();
		}
	}
}
