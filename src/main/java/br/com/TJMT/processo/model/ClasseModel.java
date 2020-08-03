package br.com.TJMT.processo.model;

import br.com.TJMT.processo.repository.entity.ClasseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class ClasseModel {

	private long id;

	private String cnpj;

	private String descricaoClasse;

	private String sigla;

	private String tipo;

	public ClasseModel() {
		super();
	}	
	public ClasseModel(long id,String cnpj,String descricaoClasse,String sigla,String tipo) {
		this.id = id;
		this.cnpj = cnpj;
		this.descricaoClasse = descricaoClasse;
		this.sigla = sigla;
		this.tipo = tipo;
	}

	public ClasseModel(ClasseEntity classeEntity) {
		this.id = classeEntity.getId();
		this.cnpj = classeEntity.getCnpj();
		this.descricaoClasse = classeEntity.getDescricaoClasse();
		this.sigla = classeEntity.getSigla();
		this.tipo = classeEntity.getTipo();
	}
}
