package org.andvicoso.shopand.model;

public enum Gender {

	MALE("Masculino"),
	FEMALE("Feminino");

	private String	description;

	private Gender(final String pDescription) {
		description = pDescription;
	}

	public String getDescription() {
		return description;
	}

	public String getKey() {
		return name();
	}

}
