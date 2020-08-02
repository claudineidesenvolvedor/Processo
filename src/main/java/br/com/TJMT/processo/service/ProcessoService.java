package br.com.TJMT.processo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.TJMT.processo.model.JuizModel;
import br.com.TJMT.processo.model.ProcessoModel;
import br.com.TJMT.processo.model.ResponseModel;
import br.com.TJMT.processo.repository.ProcessoRepository;
import br.com.TJMT.processo.repository.entity.JuizEntity;
import br.com.TJMT.processo.repository.entity.ProcessoEntity;
import br.com.TJMT.processo.util.enuns.TipoParte;

@RestController
@RequestMapping("/service")
public class ProcessoService {

	@Autowired
	private ProcessoRepository processoRepository;

	@Autowired
	private EntityManager em;

	/**
	 * SALVAR UM NOVO PROCESSO
	 * 
	 * @param processoModel
	 * @return
	 */
	@RequestMapping(value = "/processo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody ProcessoModel processoModel) {

		try {

			if (processoModel.getListaPartes().size() == 0)
				new Exception("Um processo somente poderá ser cadastrado junto com as partes..");

			if (processoModel.isNaoTemClasse())
				new Exception("Não tem Classe junto ao processo.");
			if (processoModel.isNenor())
				new Exception("Obrigatório a inclusão de duas partes no processo.");

			for (int i = 0; i <= processoModel.getListaPartes().size(); i++) {
				TipoParte parte = processoModel.getListaPartes().get(i).getTipoParte();
				if (processoModel.isJaExite(parte))
					new Exception(" Um processo não poderá ser cadastrado com tipos de partes iguais.");
			}

			processoModel = new ProcessoModel(this.processoRepository.save(new ProcessoEntity(processoModel)));
			processoModel.setNrProcesso(processoModel.gerarNumeroProcesso());
			this.processoRepository.save(new ProcessoEntity(processoModel));
			return new ResponseModel(1, "Registro salvo com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * ATUALIZAR O REGISTRO DE UM PROCESSO
	 * 
	 * @param processoModel
	 * @return
	 */
	@RequestMapping(value = "/processo", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody ProcessoModel processoModel) {

		try {

			this.processoRepository.save(new ProcessoEntity(processoModel));

			return new ResponseModel(1, "Registro atualizado com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * CONSULTAR TODAS OS PROCESSOS
	 * 
	 * @return listaModel
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/processo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<ProcessoModel> consultar() {
		final List<ProcessoModel> listaModel = new ArrayList();

		List<ProcessoEntity> listaEntity = this.processoRepository.findAll();

		listaEntity.forEach(entity -> {
			listaModel.add(new ProcessoModel(entity));
		});
		return listaModel;
	}

	/**
	 * BUSCAR UM PROCESSOS PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/processo/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ProcessoModel buscar(@PathVariable("codigo") Long codigo) {
		return new ProcessoModel(this.processoRepository.findById(codigo));
	}

	/**
	 * BUSCAR UM JUIZ PELO NOME
	 * 
	 * @param nome
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/processo/juiz/{nome}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<JuizModel> buscar(@PathVariable("nome") String nome) {
		nome = "%" + nome + "%";
		Query query = em.createQuery("SELECT j FROM JuizEntity j where j.nome like :nome", JuizEntity.class)
				.setParameter("nome", nome);
		return query.getResultList();

	}

}
