package br.com.TJMT.processo.model;

import java.util.Calendar;

import br.com.TJMT.processo.repository.entity.JuizEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JuizModel {

	private long id;

	private String nome;

	private String cpf;

	private Calendar dataNascimento;
	
	private int qtsProcesso;

	public JuizModel() {
		super();
	}

	public JuizModel(JuizEntity juizEntity) {
		this.id = juizEntity.getId();
		this.nome = juizEntity.getNome();
		this.cpf = juizEntity.getCpf();
		this.dataNascimento = juizEntity.getDataNascimento();

	}

}
