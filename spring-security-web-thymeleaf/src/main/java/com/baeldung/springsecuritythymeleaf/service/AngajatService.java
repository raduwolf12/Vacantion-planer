package com.baeldung.springsecuritythymeleaf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baeldung.springsecuritythymeleaf.dto.AngajatDto;
import com.baeldung.springsecuritythymeleaf.model.Angajat;
import com.baeldung.springsecuritythymeleaf.repository.AngajatRepository;

@Service
public class AngajatService {
	@Autowired
	private AngajatRepository repository;

	public List<Angajat> getAngajati() {
		return repository.findAll();
	}

	public Angajat getAngajat(Long id) {
		Optional<Angajat> angajatOpt = repository.findById(id);
		return angajatOpt.orElse(null);
	}

	public Angajat createAngajat(Angajat angajat) {
		return repository.save(angajat);
	}

	public Angajat updateAngajat(Angajat angajat) {
		return repository.save(angajat);
	}

	public void deleteAngajat(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
	}

	public boolean login(String name, String password) {
		return repository.login(name, password);
	}

	public Angajat findByEmail(String email) {
		return repository.getAngajatByEmail(email);
	}

	public List<AngajatDto> getAngajatiEchipe(Long id) {
		List<Angajat> list = repository.getAngajatiBySefId(id);
		List<AngajatDto> listDto = new ArrayList<AngajatDto>(); 
		for(Angajat angajat:list)
		{
			listDto.add(convertToDto(angajat));
		}
		return listDto;
	}
	
	public List<AngajatDto> getAngajatiEchipeIncludingSef(Long id) {
		List<Angajat> list = repository.getAngajatiBySefIdIncludingSef(id);
		List<AngajatDto> listDto = new ArrayList<AngajatDto>(); 
		for(Angajat angajat:list)
		{
			listDto.add(convertToDto(angajat));
		}
		return listDto;
	}
	

	private AngajatDto convertToDto(Angajat angajat) {
		ModelMapper modelMapper = new ModelMapper();
		AngajatDto angajatDto = modelMapper.map(angajat, AngajatDto.class);
		return angajatDto;
	}
}
