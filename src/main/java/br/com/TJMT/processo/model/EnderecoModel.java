package br.com.TJMT.processo.model;

import br.com.TJMT.processo.repository.entity.EnderecoEntity;
import br.com.TJMT.processo.repository.entity.ParteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnderecoModel {

	private long id;

	private String bairro;

	private String cidade;

	private String estado;

	private String cep;

	private String logradouro;

	private Integer numero;

	private ParteModel parteModel;

	public EnderecoModel() {
		super();
	}

	public EnderecoModel(long id, String bairro, String cidade, String estado, String cep, String logradouro,
			Integer numero, ParteEntity parteEntity) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.parteModel = new ParteModel(parteEntity);
	}

	public EnderecoModel(EnderecoEntity enderecoEntity) {
		this.id = enderecoEntity.getId();
		this.logradouro = enderecoEntity.getLogradouro();
		this.numero = enderecoEntity.getNumero();
		this.cep = enderecoEntity.getCep();
		this.bairro = enderecoEntity.getBairro();
		this.cidade = enderecoEntity.getCidade();
		this.estado = enderecoEntity.getEstado();
		this.parteModel = new ParteModel(enderecoEntity.getParteEntity());
	}
}
