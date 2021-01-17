package com.baeldung.springsecuritythymeleaf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "angajati")
public class Angajat {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "angajat_id", unique = true, nullable = false)
	private Long id;
	@Column(name = "angajat_name", length = 100)
	private String angajatName;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "password", length = 100)
	private String password;

	public Angajat() {

	}

	public Angajat(String angajatName) {
		super();
		this.angajatName = angajatName;
	}

	public Angajat(String angajatName, String email, String password) {
		super();
		this.angajatName = angajatName;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAngajatName() {
		return angajatName;
	}

	public void setAngajatName(String angajatName) {
		this.angajatName = angajatName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
