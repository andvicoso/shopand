package org.andvicoso.shopand.model;

public enum PaymentStatus {

	WAITING("Esperando", "warning"), PAID("Pago", "success"), ERROR("Erro",
			"error");

	private String description;
	private String image;

	private PaymentStatus(final String pDescription, final String pImage) {
		description = pDescription;
		image = pImage;
	}

	public String getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

	public String getKey() {
		return name();
	}
}
