package br.com.TJMT.processo.repository.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import br.com.TJMT.processo.model.ParteModel;
import br.com.TJMT.processo.util.enuns.TipoParte;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "tb_classe")
public class ParteEntity {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@PrimaryKeyJoinColumn(name = "id")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Calendar dataNascimento;

	@CPF(message = "CPF inváldo")
	@NotEmpty( message = "O cpf é de preenchimento obrigatório.")
	@Column(name = "cpf", nullable = false, length = 11, unique = true)
	private String cpf;

	@Enumerated(EnumType.STRING)
	@NotEmpty( message = "O tipo parte é de preenchimento obrigatório.")
	@Column(name = "tipo_parte", nullable = false, length = 45)
	private TipoParte tipoParte;

	public ParteEntity() {
		super();
	}

	public ParteEntity(long id, Calendar dataNascimento, String cpf, TipoParte tipoParte) {
		this.id = id;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.tipoParte = tipoParte;
	}

	public ParteEntity(ParteModel parteModel) {
		this.id = parteModel.getId();
		this.dataNascimento = parteModel.getDataNascimento();
		this.cpf = parteModel.getCpf();
		this.tipoParte = parteModel.getTipoParte();
	}

}
