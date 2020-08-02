package br.com.TJMT.processo.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.TJMT.processo.repository.entity.ClasseEntity;
import br.com.TJMT.processo.repository.entity.JuizEntity;
import br.com.TJMT.processo.repository.entity.ParteEntity;
import br.com.TJMT.processo.repository.entity.ProcessoEntity;
import br.com.TJMT.processo.util.enuns.TipoParte;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProcessoModel {

	private long id;

	private String nrProcesso;

	private Calendar dataCriacao;

	private Calendar dataDistribuicao;

	private ClasseModel classeModel;

	private List<JuizModel> listaJuizes;

	private List<ParteModel> listaPartes;

	public ProcessoModel() {
		super();
	}

	public ProcessoModel(long id, String nrProcesso, Calendar dataCriacao, Calendar dataDistribuicao,
			ClasseEntity classeEntityl, List<JuizEntity> listaJuizes, List<ParteEntity> listaPartes) {
		this.id = id;
		this.nrProcesso = nrProcesso;
		this.dataCriacao = dataCriacao;
		this.dataDistribuicao = dataDistribuicao;
		this.classeModel = new ClasseModel(classeEntityl);
		this.listaJuizes = carregarJuiz(listaJuizes);
		this.listaPartes = carregarParte(listaPartes);
	}

	public ProcessoModel(ProcessoEntity processoEntity) {
		this.id = processoEntity.getId();
		this.nrProcesso = processoEntity.getNrProcesso();
		this.dataCriacao = processoEntity.getDataCriacao();
		this.dataDistribuicao = processoEntity.getDataDistribuicao();
		this.classeModel = new ClasseModel(processoEntity.getClasseEntity());
		this.listaJuizes = carregarJuiz(processoEntity.getListaJuizes());
		this.listaPartes = carregarParte(processoEntity.getListaPartes());
	}

	private List<JuizModel> carregarJuiz(List<JuizEntity> listaEntity) {
		List<JuizModel> lista = new ArrayList<>();
		listaEntity.forEach(entity -> {
			lista.add(new JuizModel(entity));
		});
		return lista;
	}

	private List<ParteModel> carregarParte(List<ParteEntity> listaEntity) {
		List<ParteModel> lista = new ArrayList<>();
		listaEntity.forEach(entity -> {
			lista.add(new ParteModel(entity));
		});
		return lista;
	}

	public String gerarNumeroProcesso() {
		String nr = null;
		Calendar data = Calendar.getInstance();
		nr = StringUtils.leftPad(Long.toString(this.id), 7, "0");
		// Na pagina do TJMT falar dia.
		// nr += data.get(Calendar.DAY_OF_MONTH);
		// no processo pede mês
		nr += "-" + data.get(Calendar.MONTH);
		nr += "." + data.get(Calendar.YEAR) + ".811";
		return nr;
	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean isJaExite(TipoParte parte) {
		if (this.listaPartes.contains(parte))
			return true;
		else
			return false;
	}

	public boolean isNenor() {
		if (this.listaPartes.size() < 2)
			return true;
		else
			return false;
	}

	public boolean isNaoTemClasse() {
		if (StringUtils.isNoneBlank(this.classeModel.getDescricaoClasse()))
			return true;
		else
			return false;
	}

}
