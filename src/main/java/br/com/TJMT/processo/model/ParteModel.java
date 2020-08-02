package br.com.TJMT.processo.model;

import java.util.Calendar;

import br.com.TJMT.processo.repository.entity.ParteEntity;
import br.com.TJMT.processo.util.enuns.TipoParte;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParteModel {

	private long id;

	private Calendar dataNascimento;

	private String cpf;

	private TipoParte tipoParte;

	public ParteModel() {
		super();
	}

	public ParteModel(ParteEntity parteEntity) {
		this.id = parteEntity.getId();
		this.dataNascimento = parteEntity.getDataNascimento();
		this.cpf = parteEntity.getCpf();
		this.tipoParte = parteEntity.getTipoParte();
	}

}
