package br.com.TJMT.processo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;
import org.springframework.stereotype.Service;

import br.com.TJMT.processo.model.JuizModel;
import br.com.TJMT.processo.repository.JuizRepository;
import br.com.TJMT.processo.repository.ProcessoRepository;
import br.com.TJMT.processo.repository.entity.JuizEntity;
import br.com.TJMT.processo.repository.entity.ProcessoEntity;

//@RestController
//@RequestMapping("/service")
@Service
public class DistribuicaoService extends Thread {

	private static final int TIMEOUT = 5000;

	@Autowired
	private EntityManager em;

	@Autowired
	private JuizRepository juizRepository;

	@Autowired
	private ProcessoRepository processoRepository;

	List<JuizModel> distribui = new ArrayList<JuizModel>();

	public void run() {
		try {

			Thread.sleep(TIMEOUT);
			distribuiProcesso();
			System.out.println("ThreadA is waked up ");
		} catch (InterruptedException e) {
			System.out.println("Sleep of ThreadA get interrupted ");
		}
		System.out.println("ThreadA completed");
	}

	/**
	 * DISTRIBUI TODOS OS PROCESSO POR JUIZ COM MENOR PROCESSO
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/distribui", method = RequestMethod.GET, produces =
	 * MediaType.APPLICATION_JSON_UTF8_VALUE) public @ResponseBody void
	 * distribuiProcesso() { JuizModel juizMenosProcesso = new JuizModel(); int
	 * menor = 0; for (ProcessoEntity entity : buscarProcesso()) { for (JuizModel
	 * juizModel : listaJuizes()) { if (juizModel.getQtsProcesso() < menor)
	 * juizMenosProcesso = juizModel; }
	 * entity.setDataDistribuicao(Calendar.getInstance());
	 * entity.getListaJuizes().add(new JuizEntity(juizMenosProcesso));
	 * this.processoRepository.save(entity); }
	 * 
	 * }
	 */
	public void distribuiProcesso() {
		JuizModel juizMenosProcesso = new JuizModel();
		int menor = 0;
		for (ProcessoEntity entity : buscarProcesso()) {
			for (JuizModel juizModel : listaJuizes()) {
				if (juizModel.getQtsProcesso() < menor)
					juizMenosProcesso = juizModel;
			}
			entity.setDataDistribuicao(Calendar.getInstance());
			entity.getListaJuizes().add(new JuizEntity(juizMenosProcesso));
			this.processoRepository.save(entity);
		}

	}

	private List<JuizModel> listaJuizes() {
		List<JuizModel> listaModel = new ArrayList<JuizModel>();

		List<JuizEntity> listaEntity = this.juizRepository.findAll();

		for (JuizEntity juizEntity : listaEntity) {
			JuizModel juizModel = new JuizModel(juizEntity);
			juizModel.setQtsProcesso(buscar(juizModel));
			listaModel.add(juizModel);
		}
		return listaModel;
	}

	/**
	 * BUSCAR QTS PROCESSO QUE JUIZ TEM
	 * 
	 * @param
	 * @return int
	 */
	private int buscar(JuizModel juizModel) {
		Query query = em.createQuery("SELECT j FROM JuizEntity j where j.id= :id", JuizEntity.class).setParameter("id",
				juizModel.getId());
		return query.getResultList().size();
	}

	/**
	 * BUSCAR QTS PROCESSO QUE JUIZ TEM
	 * 
	 * @param
	 * @return int
	 */
	@SuppressWarnings({ "unchecked" })
	private List<ProcessoEntity> buscarProcesso() {
		Query query = this.em.createQuery("SELECT p FROM ProcessoEntity p where p.dataDistribuicao is null",
				ProcessoEntity.class);
		return query.getResultList();
	}

}
