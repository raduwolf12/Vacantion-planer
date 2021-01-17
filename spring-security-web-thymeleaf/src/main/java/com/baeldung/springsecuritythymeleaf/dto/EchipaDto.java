package com.baeldung.springsecuritythymeleaf.dto;

import java.util.List;

import com.baeldung.springsecuritythymeleaf.model.Angajat;

public class EchipaDto {
	private Long id;
	private String echipaName;

	private List<Angajat> angajati;

	EchipaDto() {

	}

	public EchipaDto(Long id, String echipaName, List<Angajat> angajati) {
		super();
		this.id = id;
		this.echipaName = echipaName;
		this.angajati = angajati;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEchipaName() {
		return echipaName;
	}

	public void setEchipaName(String echipaName) {
		this.echipaName = echipaName;
	}

	public List<Angajat> getAngajati() {
		return angajati;
	}

	public void setAngajati(List<Angajat> angajati) {
		this.angajati = angajati;
	}

}
