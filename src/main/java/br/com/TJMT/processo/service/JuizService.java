package br.com.TJMT.processo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.TJMT.processo.model.JuizModel;
import br.com.TJMT.processo.model.ResponseModel;
import br.com.TJMT.processo.repository.JuizRepository;
import br.com.TJMT.processo.repository.entity.JuizEntity;

@RestController
@RequestMapping("/service")
public class JuizService {

	@Autowired
	private JuizRepository juizRepository;

	/**
	 * SALVAR UM NOVO REGISTRO
	 * 
	 * @param juizModel
	 * @return
	 */
	@RequestMapping(value = "/juiz", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody JuizModel juizModel) {

		try {

			this.juizRepository.save(new JuizEntity(juizModel));

			return new ResponseModel(1, "Registro salvo com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * ATUALIZAR O REGISTRO DE UMA JUIZ
	 * 
	 * @param juizModel
	 * @return
	 */
	@RequestMapping(value = "/juiz", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody  JuizModel juizModel)  {

		try {

			this.juizRepository.save(new JuizEntity(juizModel));

			return new ResponseModel(1, "Registro atualizado com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * CONSULTAR TODOS OS JUIZES
	 * 
	 * @return listaModel
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/juiz", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<JuizModel> consultar() {
		final List<JuizModel> listaModel = new ArrayList();

		List<JuizEntity> listaEntity = this.juizRepository.findAll();

		listaEntity.forEach(entity -> {
			listaModel.add(new JuizModel(entity));
		});
		return listaModel;
	}

	/**
	 * BUSCAR UM JUIZ PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/juiz/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody JuizModel buscar(@PathVariable("codigo") Long codigo) {

		return new JuizModel(this.juizRepository.findById(codigo));
	}

	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/juiz/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") long codigo) {

		JuizModel juizModel = new JuizModel(this.juizRepository.findById(codigo));

		try {

			this.juizRepository.delete(new JuizEntity(juizModel));

			return new ResponseModel(1, "Registro excluido com sucesso!");

		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}

}
