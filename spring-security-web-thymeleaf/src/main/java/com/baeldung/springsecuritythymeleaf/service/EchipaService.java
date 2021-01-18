package com.baeldung.springsecuritythymeleaf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baeldung.springsecuritythymeleaf.model.Angajat;
import com.baeldung.springsecuritythymeleaf.model.Echipa;
import com.baeldung.springsecuritythymeleaf.repository.AngajatRepository;
import com.baeldung.springsecuritythymeleaf.repository.EchipaRepository;

@Service
public class EchipaService {
	@Autowired
	private EchipaRepository repository;
	@Autowired
	private AngajatRepository angajatRepository;

	public List<Echipa> getEchipa() {
		return repository.findAll();
	}

	public Echipa getEchipa(Long id) {
		Optional<Echipa> echipaOpt = repository.findById(id);
		return echipaOpt.orElse(null);
	}

	public Echipa createEchipa(Echipa echipa) {
		return repository.save(echipa);
	}

	public Echipa updateEchipa(Echipa echipa) {
		return repository.save(echipa);
	}

	public void deleteEchipa(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
	}

	public void addAngajatToEchipa(Long idAngajat, Long idEchipa) {
		Optional<Echipa> echipaOpt = repository.findById(idEchipa);
		Optional<Angajat> angajatOpt = angajatRepository.findById(idAngajat);
		echipaOpt.get().getAngajati().add(angajatOpt.get());
		repository.save(echipaOpt.get());

	}
	
	public List<Echipa> getEchipeAngajat(Long angajatId)
	{
		return repository.getEchipeAngajat(angajatId);
	}
	public List<Echipa> getEchipeGestionateDeAngajat(Long angajatId)
	{
		return repository.getEchipeMenagedByAngajat(angajatId);
	}
	
	public void deleteEchipa2(String id)
	{
//		repository.alter1();
//		repository.alter2();
		repository.deleteEchipa2(Integer.valueOf(id));
//		repository.alter3();
//		repository.alter4();
	}

}
