package com.rodrigofujioka.dev.web.rest;

import com.rodrigofujioka.dev.web.domain.Quarentena;
import com.rodrigofujioka.dev.web.service.QuarentenaService;
import com.rodrigofujioka.dev.web.service.dto.QuarentenaNomeCidadeDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//www.rodrigofujioka.com
//localhost:8080
//localhost:8080/api
//localhost:8080/api/quarentena

@RestController
@RequestMapping("/api")
public class QuarentenaRest {

	@Autowired
	private QuarentenaService quarentenaService;
	// private QuarentenaRepository quarentenaRepository;

	@GetMapping("/quarentena/{estado}")
	public ResponseEntity<List<Quarentena>> getQuarentena(@PathVariable String estado) {
		return ResponseEntity.ok(quarentenaService.getQuarentenaByUf(estado));
	}

	@PostMapping("/quarentena")
	public ResponseEntity<Quarentena> salvar(@RequestBody @Valid Quarentena quarentena) {
		Quarentena quarentenaSalva = quarentenaService.salvar(quarentena);
		return ResponseEntity.ok().body(quarentenaSalva);
	}

	@GetMapping("/quarentena")
	public ResponseEntity<List<Quarentena>> getQuarentena() {
		return ResponseEntity.ok(quarentenaService.getQuarentena());
	}

	@GetMapping("/quarentena/{id}")
	public ResponseEntity<Quarentena> getQuarentenaById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(quarentenaService.getQuarentenaById(id));
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error("not found");
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/quarentena/nome/cidade/{id}")
	public ResponseEntity<QuarentenaNomeCidadeDTO> getQuarentenaNomeCidadeById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(quarentenaService.getQuarentenaNomeCidadeById(id));
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error("not found");
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/quarentena/{id}")
	public ResponseEntity<Quarentena> deleteById(@PathVariable Long id) {
		try {
			quarentenaService.deleteQuarentenaById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error("not found");
			return ResponseEntity.notFound().build();
		}
	}

}
