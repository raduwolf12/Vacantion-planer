package com.baeldung.springsecuritythymeleaf.model;

public class StatusDto {

	private Integer id;

	private String name;

	public boolean isSelected(Integer userId) {
		if (userId != null) {
			return userId.equals(id);
		}
		return false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StatusDto() {
	}

	public StatusDto(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
