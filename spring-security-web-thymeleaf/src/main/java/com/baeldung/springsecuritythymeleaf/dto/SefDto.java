package com.baeldung.springsecuritythymeleaf.dto;

import java.util.List;

import com.baeldung.springsecuritythymeleaf.model.Angajat;
import com.baeldung.springsecuritythymeleaf.model.Echipa;

public class SefDto {
	private Long id;

	private boolean isCeo;

	private Angajat angajatId;

	private List<Echipa> echipe;

	SefDto() {

	}

	public SefDto(Long id, boolean isCeo, Angajat angajatId, List<Echipa> echipe) {
		super();
		this.id = id;
		this.isCeo = isCeo;
		this.angajatId = angajatId;
		this.echipe = echipe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isCeo() {
		return isCeo;
	}

	public void setCeo(boolean isCeo) {
		this.isCeo = isCeo;
	}

	public Angajat getAngajatId() {
		return angajatId;
	}

	public void setAngajatId(Angajat angajatId) {
		this.angajatId = angajatId;
	}

	public List<Echipa> getEchipe() {
		return echipe;
	}

	public void setEchipe(List<Echipa> echipe) {
		this.echipe = echipe;
	}

}
