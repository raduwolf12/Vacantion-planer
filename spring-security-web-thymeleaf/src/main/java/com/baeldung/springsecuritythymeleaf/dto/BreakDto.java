package com.baeldung.springsecuritythymeleaf.dto;

import java.util.List;

import com.baeldung.springsecuritythymeleaf.model.Angajat;
import com.baeldung.springsecuritythymeleaf.model.enums.Status;

public class BreakDto {
	private Long id;
	private String startDate;
	private String endDate;

	private Status status;

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

	public BreakDto() {

	}

	public BreakDto(String startDate, String endDate, Status status, List<Angajat> angajati) {
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
