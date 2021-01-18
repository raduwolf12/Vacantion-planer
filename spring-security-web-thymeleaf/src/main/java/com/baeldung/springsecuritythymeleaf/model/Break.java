package com.baeldung.springsecuritythymeleaf.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.baeldung.springsecuritythymeleaf.model.enums.Status;

@Entity
@Table(name = "breaks")
public class Break {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "breaks_id", unique = true, nullable = false)
	private Long id;
	@Column(name = "startDate", length = 100)
	private String startDate;
	@Column(name = "endDate", length = 100)
	private String endDate;
	
	@Column(name = "status", length = 100)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "angajat_id")
	private List<Angajat> angajati;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<Angajat> getAngajati() {
		return angajati;
	}

	public void setAngajati(List<Angajat> angajati) {
		this.angajati = angajati;
	}
	public Break() {
		
	}

	public Break( String startDate, String endDate, Status status, List<Angajat> angajati) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.angajati = angajati;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
	
}
