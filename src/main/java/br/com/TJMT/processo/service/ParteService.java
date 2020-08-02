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

import br.com.TJMT.processo.model.ParteModel;
import br.com.TJMT.processo.model.ResponseModel;
import br.com.TJMT.processo.repository.ParteRepository;
import br.com.TJMT.processo.repository.entity.ParteEntity;

@RestController
@RequestMapping("/service")
public class ParteService {

	@Autowired
	private ParteRepository parteRepository;

	/**
	 * SALVAR UM NOVO REGISTRO
	 * 
	 * @param parteModel
	 * @return
	 */
	@RequestMapping(value = "/parte", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody ParteModel parteModel) {

		try {

			this.parteRepository.save(new ParteEntity(parteModel));

			return new ResponseModel(1, "Registro salvo com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * ATUALIZAR O REGISTRO DE UMA PARTE
	 * 
	 * @param parteModel
	 * @return
	 */
	@RequestMapping(value = "/parte", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody ParteModel parteModel) {

		try {

			this.parteRepository.save(new ParteEntity(parteModel));

			return new ResponseModel(1, "Registro atualizado com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * CONSULTAR TODAS AS PARTE
	 * 
	 * @return listaModel
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/parte", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<ParteModel> consultar() {
		final List<ParteModel> listaModel = new ArrayList();

		List<ParteEntity> listaEntity = this.parteRepository.findAll();

		listaEntity.forEach(entity -> {
			listaModel.add(new ParteModel(entity));
		});
		return listaModel;
	}

	/**
	 * BUSCAR UMA PARTE PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/parte/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ParteModel buscar(@PathVariable("codigo") Long codigo) {

		return new ParteModel(this.parteRepository.findById(codigo));
	}

	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/parte/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") long codigo) {

		ParteModel parteModel = new ParteModel(this.parteRepository.findById(codigo));

		try {

			this.parteRepository.delete(new ParteEntity(parteModel));

			return new ResponseModel(1, "Registro excluido com sucesso!");

		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}

}
