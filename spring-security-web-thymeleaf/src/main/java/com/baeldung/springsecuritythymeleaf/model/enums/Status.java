package com.baeldung.springsecuritythymeleaf.model.enums;

public enum Status {
	PENDING("Pending"),
	CONFIRMED("Confirmed"),
	CANCELED("Canceled"),
	BADINTERVAL("Bad interval!");
	private String val;

	Status(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}
}
