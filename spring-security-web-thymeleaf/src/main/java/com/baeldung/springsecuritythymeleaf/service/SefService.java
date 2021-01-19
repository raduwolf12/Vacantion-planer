package com.baeldung.springsecuritythymeleaf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baeldung.springsecuritythymeleaf.model.Echipa;
import com.baeldung.springsecuritythymeleaf.model.Sef;
import com.baeldung.springsecuritythymeleaf.repository.EchipaRepository;
import com.baeldung.springsecuritythymeleaf.repository.SefRepository;

@Service
public class SefService {
	@Autowired
	private SefRepository repository;

	@Autowired
	private EchipaRepository echipaRepository;

	public Sef getCeo() {
		return repository.getCeo().get(0);
	}

	public List<Sef> getSefi() {
		return repository.findAll();
	}

	public Sef getSef(Long id) {
		Optional<Sef> sefOpt = repository.findById(id);
		return sefOpt.orElse(null);
	}

	public Sef createSef(Sef sef) {
		return repository.save(sef);
	}

	public Sef updateSef(Sef sef) {
		return repository.save(sef);
	}

	public void deleteSef(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
	}

	public void addSefToTeam(Long idSef, Long idEchipa) {
		Optional<Sef> sefOpt = repository.findById(idSef);
		Optional<Echipa> echipaOpt = echipaRepository.findById(idEchipa);
		sefOpt.get().getEchipe().add(echipaOpt.get());
		repository.save(sefOpt.get());
	}

	public Object getAngajatiEchipeForSefId(Long id) {
		return echipaRepository;
	}

	public Sef getSefByAngajatId(Long id) {
		return repository.getSefByAngajatId(id);
	}

	public boolean isCeo(Long id) {

		if (repository.isCeo(id) != null) {
			return true;
		} else {
			return false;
		}
	}
}
