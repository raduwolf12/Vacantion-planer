package com.baeldung.springsecuritythymeleaf.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "echipe")
public class Echipa {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "echipa_id", unique = true, nullable = false)
	private Long id;
	@Column(name = "echipa_name", length = 100)
	private String echipaName;

	@ManyToMany(cascade = { CascadeType.MERGE,}, fetch = FetchType.EAGER)
	@JoinColumn(name = "angajat_id")
	private List<Angajat> angajati;

	Echipa() {

	}
	public Echipa( String echipaName, List<Angajat> angajati) {
		super();
		this.echipaName = echipaName;
		this.angajati = angajati;
	}
	public Echipa(Long id, String echipaName, List<Angajat> angajati) {
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
