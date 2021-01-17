package com.baeldung.springsecuritythymeleaf.dto;

public class AngajatDto {
	private Long id;

	private String angajatName;

	private String email;

	private String password;

	public AngajatDto() {

	}

	public AngajatDto(String angajatName) {
		super();
		this.angajatName = angajatName;
	}

	public AngajatDto(String angajatName, String email, String password) {
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
