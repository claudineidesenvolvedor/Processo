package br.com.TJMT.processo.repository.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.TJMT.processo.model.ClasseModel;
import br.com.TJMT.processo.model.JuizModel;
import br.com.TJMT.processo.model.ParteModel;
import br.com.TJMT.processo.model.ProcessoModel;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_processo")
public class ProcessoEntity {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@PrimaryKeyJoinColumn(name = "id")
	private long id;

	@Column(name = "nr_processo", length = 45)
	private String nrProcesso;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_criacao")
	private Calendar dataCriacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_distribuicao")
	private Calendar dataDistribuicao;

	@OneToOne
	@JoinColumn(name = "id_classe", foreignKey = @ForeignKey(name = "processoClasseFK"))
	private ClasseEntity classeEntity;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tb_processo_parte", joinColumns = @JoinColumn(name = "id_processo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "processoJuizFK")), inverseJoinColumns = @JoinColumn(name = "id_juiz", referencedColumnName = "id", foreignKey = @ForeignKey(name = "juizProcessoFK")))
	private List<JuizEntity> listaJuizes;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tb_processo_juiz", joinColumns = @JoinColumn(name = "id_processo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "processoParteFK")), inverseJoinColumns = @JoinColumn(name = "id_parte", referencedColumnName = "id", foreignKey = @ForeignKey(name = "parteProcessoFK")))
	private List<ParteEntity> listaPartes;

	public ProcessoEntity() {
		super();
	}

	public ProcessoEntity(long id, String nrProcesso, Calendar dataCriacao, Calendar dataDistribuicao,
			ClasseModel classeModel, List<JuizModel> listaJuizes, List<ParteModel> listaPartes) {
		this.id = id;
		this.nrProcesso = nrProcesso;
		this.dataCriacao = dataCriacao;
		this.dataDistribuicao = dataDistribuicao;
		this.classeEntity = new ClasseEntity(classeModel);
		this.listaJuizes = carregarJuiz(listaJuizes);
		this.listaPartes = carregarParte(listaPartes);
	}

	public ProcessoEntity(ProcessoModel processoModel) {
		this.id = processoModel.getId();
		this.nrProcesso = processoModel.getNrProcesso();
		this.dataCriacao = processoModel.getDataCriacao();
		this.dataDistribuicao = processoModel.getDataDistribuicao();
		this.classeEntity = new ClasseEntity(processoModel.getClasseModel());
		this.listaJuizes = carregarJuiz(processoModel.getListaJuizes());
		this.listaPartes = carregarParte(processoModel.getListaPartes());
	}

	private List<JuizEntity> carregarJuiz(List<JuizModel> listaModel) {
		List<JuizEntity> lista = new ArrayList<>();
		listaModel.forEach(entity -> {
			lista.add(new JuizEntity(entity));
		});
		return lista;
	}

	private List<ParteEntity> carregarParte(List<ParteModel> listaModel) {
		List<ParteEntity> lista = new ArrayList<>();
		listaModel.forEach(entity -> {
			lista.add(new ParteEntity(entity));
		});
		return lista;
	}

}