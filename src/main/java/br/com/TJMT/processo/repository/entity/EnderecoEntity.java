package br.com.TJMT.processo.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.TJMT.processo.model.EnderecoModel;
import br.com.TJMT.processo.model.ParteModel;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_endereco_parte")
public class EnderecoEntity {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@PrimaryKeyJoinColumn(name = "id")
	private long id;

	@NotEmpty( message = "O bairro é de preenchimento obrigatório.")
	@Column(name = "bairro", nullable = false, length = 100)
	private String bairro;

	@NotEmpty( message = "O cidade é de preenchimento obrigatório.")
	@Column(name = "cidade", nullable = false, length = 100)
	private String cidade;

	@NotEmpty( message = "O cpf é de preenchimento obrigatório.")
	@Column(name = "estado", nullable = false, length = 45)
	private String estado;

	@NotEmpty( message = "O cep é de preenchimento obrigatório.")
	@Column(name = "cep", nullable = false, length = 45)
	private String cep;

	@NotEmpty( message = "O logradouro é de preenchimento obrigatório.")
	@Column(name = "logradouro", nullable = false, length = 100)
	private String logradouro;

	@NotEmpty( message = "O numero é de preenchimento obrigatório.")
	@Column(name = "numero", nullable = false)
	private Integer numero;

	@OneToOne
	@JoinColumn(name = "id_parte", foreignKey = @ForeignKey(name = "enderecoParteFK"))
	private ParteEntity parteEntity;

	public EnderecoEntity() {
		super();
	}

	public EnderecoEntity(long id, String bairro, String cidade, String estado, String cep, String logradouro,
			Integer numero, ParteModel parteModel) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.parteEntity = new ParteEntity(parteModel);
	}

	public EnderecoEntity(EnderecoModel enderecoModel) {
		this.id = enderecoModel.getId();
		this.logradouro = enderecoModel.getLogradouro();
		this.numero = enderecoModel.getNumero();
		this.cep = enderecoModel.getCep();
		this.bairro = enderecoModel.getBairro();
		this.cidade = enderecoModel.getCidade();
		this.estado = enderecoModel.getEstado();
		this.parteEntity = new ParteEntity(enderecoModel.getParteModel());
	}
}
