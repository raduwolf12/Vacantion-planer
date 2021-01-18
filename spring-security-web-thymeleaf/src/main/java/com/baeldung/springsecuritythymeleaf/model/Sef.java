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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sefi")
public class Sef {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "sef_id", unique = true, nullable = false)
	private Long id;

	@Column(name = "ceo", nullable = false)
	private boolean isCeo;

	@ManyToOne(optional = false, cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "angajat_id")
	private Angajat angajatId;

	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "echipa_id")
	private List<Echipa> echipe;

	Sef() {

	}

	public Sef(boolean isCeo, Angajat angajatId, List<Echipa> echipe) {
		super();
		this.isCeo = isCeo;
		this.angajatId = angajatId;
		this.echipe = echipe;
	}

	public Sef(Long id, boolean isCeo, Angajat angajatId, List<Echipa> echipe) {
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
