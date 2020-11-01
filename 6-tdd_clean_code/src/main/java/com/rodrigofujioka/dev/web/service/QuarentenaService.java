package com.rodrigofujioka.dev.web.service;

import com.rodrigofujioka.dev.web.domain.Quarentena;
import com.rodrigofujioka.dev.web.repository.QuarentenaRepository;
import com.rodrigofujioka.dev.web.service.dto.QuarentenaNomeCidadeDTO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuarentenaService {

	@Autowired
	private QuarentenaRepository quarentenaRepository;

	public Quarentena salvar(Quarentena quarentena) {
		if (quarentena.getUf().equalsIgnoreCase("PB"))
			throw new RuntimeException("Estado não possui teste");
		return quarentenaRepository.save(quarentena);
	}

	public List<Quarentena> getQuarentenaByUf(String uf) {
		return quarentenaRepository.findQuarentenaByUf(uf);
	}

	public List<Quarentena> getQuarentena() {
		return quarentenaRepository.findAll();
	}

	public Quarentena getQuarentenaById(Long id) throws NotFoundException {
		Optional<Quarentena> quarentena = quarentenaRepository.findById(id);
		if (quarentena.isEmpty())
			throw new NotFoundException("Quarentena não localizada");
		return quarentena.get();
	}

	public QuarentenaNomeCidadeDTO getQuarentenaNomeCidadeById(Long id) throws NotFoundException {
		Optional<Quarentena> quarentena = quarentenaRepository.findById(id);
		if (quarentena.isEmpty())
			throw new NotFoundException("Quarentena não localizada");
		else
			return new QuarentenaNomeCidadeDTO(quarentena.get());
	}


	public void deleteQuarentenaById(Long id) throws NotFoundException {
		Optional<Quarentena> quarentena = quarentenaRepository.findById(id);
		if (quarentena.isEmpty())
			throw new NotFoundException("Quarentena não localizada");
		else
			quarentenaRepository.delete(quarentena.get());
	}

}
