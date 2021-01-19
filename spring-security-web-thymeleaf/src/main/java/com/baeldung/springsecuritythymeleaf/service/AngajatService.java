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

	public List<AngajatDto> getAngajati() {
		List<Angajat> angajati = repository.findAll();
		List<AngajatDto> angajatDtos = new ArrayList<AngajatDto>();
		for (Angajat ang : angajati) {
			angajatDtos.add(convertToDto(ang));

		}
		return angajatDtos;
	}

	public AngajatDto getAngajat(Long id) {
		Optional<Angajat> angajatOpt = repository.findById(id);

		AngajatDto angajatDtos = convertToDto(angajatOpt.orElse(null));

		return angajatDtos;
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

	public AngajatDto findByEmail(String email) {
		Angajat angajat = repository.getAngajatByEmail(email);
		AngajatDto angajatDto = convertToDto(angajat);
		return angajatDto;
	}

	public AngajatDto getAngajatByEmailAndPassword(String email, String password) {
		 
		Angajat angajat = repository.getAngajatByEmailAndPassword(email, password);
		AngajatDto angajatDto = convertToDto(angajat);
		return angajatDto;
	}

	public List<AngajatDto> getAngajatiEchipe(Long id) {
		List<Angajat> list = repository.getAngajatiBySefId(id);
		List<AngajatDto> listDto = new ArrayList<AngajatDto>();
		for (Angajat angajat : list) {
			listDto.add(convertToDto(angajat));
		}
		return listDto;
	}

	public List<AngajatDto> getAngajatiBySefIdAndSef(Long id) {
		List<Angajat> list = repository.getAngajatiBySefIdAndSef(id);
		List<AngajatDto> listDto = new ArrayList<AngajatDto>();
		for (Angajat angajat : list) {
			listDto.add(convertToDto(angajat));
		}
		return listDto;
	}

	public List<AngajatDto> getAngajatiEchipeIncludingSef(Long id) {
		List<Angajat> list = repository.getAngajatiBySefIdIncludingSef(id);
		List<AngajatDto> listDto = new ArrayList<AngajatDto>();
		for (Angajat angajat : list) {
			listDto.add(convertToDto(angajat));
		}
		return listDto;
	}

	public AngajatDto convertToDto(Angajat angajat) {
		ModelMapper modelMapper = new ModelMapper();
		AngajatDto angajatDto = modelMapper.map(angajat, AngajatDto.class);
		return angajatDto;
	}

	public Angajat convertDtoToEntity(AngajatDto angajatDto) {
		ModelMapper modelMapper = new ModelMapper();
		Angajat angajat = modelMapper.map(angajatDto, Angajat.class);
		return angajat;
	}
}
