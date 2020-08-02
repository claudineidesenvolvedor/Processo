package br.com.TJMT.processo.util.enuns;

public enum TipoParte {

	AUTOR("Autor(a)"), VITIMA("Vítima"), DENUNCIADO("Denunciado(a)");

	private String value;

	private TipoParte(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
