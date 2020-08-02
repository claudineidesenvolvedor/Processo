package br.com.TJMT.processo.repository.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import br.com.TJMT.processo.model.JuizModel;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_juiz")
public class JuizEntity {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@PrimaryKeyJoinColumn(name = "id")
	private long id;

	@NotEmpty( message = "O nome é de preenchimento obrigatório.")
	@Column(name = "nome", nullable = false, length = 150)
	private String nome;
	
	@CPF(message = "CPF inváldo")
	@NotEmpty( message = "O cpf é de preenchimento obrigatório.")
	@Column(name = "cpf", nullable = false, length = 11, unique = true)
	private String cpf;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Calendar dataNascimento;

	public JuizEntity() {
		super();
	}

	public JuizEntity(long id, String nome, String cpf, Calendar dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;

	}

	public JuizEntity(JuizModel juizModel) {
		this.id = juizModel.getId();
		this.nome = juizModel.getNome();
		this.cpf = juizModel.getCpf();
		this.dataNascimento = juizModel.getDataNascimento();

	}
}
