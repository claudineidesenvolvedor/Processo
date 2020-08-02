package br.com.TJMT.processo.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.TJMT.processo.model.ClasseModel;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_classe")
public class ClasseEntity {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@PrimaryKeyJoinColumn(name = "id")
	private long id;

	@CNPJ(message = "CNPJ inváldo")
	@NotEmpty( message = "O cnpj é de preenchimento obrigatório.")
	@Column(name = "cnpj", nullable = false, length = 20, unique = true)
	private String cnpj;

	@NotEmpty( message = "O descrição Classe é de preenchimento obrigatório.")
	@Column(name = "ds_classe", nullable = false, length = 100)
	private String descricaoClasse;

	@NotEmpty( message = "O sigla é de preenchimento obrigatório.")
	@Column(name = "sigla", nullable = false, length = 45)
	private String sigla;

	@NotEmpty( message = "O tipo é de preenchimento obrigatório.")
	@Column(name = "tipo", nullable = false, length = 45)
	private String tipo;

	public ClasseEntity() {
		super();
	}

	public ClasseEntity(long id, String cnpj, String descricaoClasse, String sigla, String tipo) {
		this.id = id;
		this.cnpj = cnpj;
		this.descricaoClasse = descricaoClasse;
		this.sigla = sigla;
		this.tipo = tipo;
	}

	public ClasseEntity(ClasseModel classeModel) {
		this.id = classeModel.getId();
		this.cnpj = classeModel.getCnpj();
		this.descricaoClasse = classeModel.getDescricaoClasse();
		this.sigla = classeModel.getSigla();
		this.tipo = classeModel.getTipo();
	}

}
