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

import br.com.TJMT.processo.model.ClasseModel;
import br.com.TJMT.processo.model.ResponseModel;
import br.com.TJMT.processo.repository.ClasseRepository;
import br.com.TJMT.processo.repository.entity.ClasseEntity;

@RestController
@RequestMapping("/service")
public class ClasseService {

	@Autowired
	private ClasseRepository classeRepository;

	/**
	 * SALVAR UM NOVO REGISTRO
	 * 
	 * @param classeModel
	 * @return
	 */
	@RequestMapping(value = "/classe", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody ClasseModel classeModel) {

		try {

			this.classeRepository.save(new ClasseEntity(classeModel));

			return new ResponseModel(1, "Registro salvo com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * ATUALIZAR O REGISTRO DE UMA CLASSE
	 * 
	 * @param classeModel
	 * @return
	*/
	@RequestMapping(value = "/classe", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody ClasseModel classeModel) {

		try {

			this.classeRepository.save(new ClasseEntity(classeModel));

			return new ResponseModel(1, "Registro atualizado com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	} 

	/**
	 * CONSULTAR TODAS AS CLASSE
	 * 
	 * @return listaModel
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/classe", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<ClasseModel> consultar() {
		final List<ClasseModel> listaModel = new ArrayList();
		
		List<ClasseEntity> listaEntity = this.classeRepository.findAll();
		
		listaEntity.forEach(entity -> {
			listaModel.add(new ClasseModel(entity));
		});
		return listaModel;
	}

	/**
	 * BUSCAR UMA CLASSE PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	*/
	@RequestMapping(value = "/classe/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ClasseModel buscar(@PathVariable("codigo") Long codigo) {

		return new ClasseModel(this.classeRepository.findById(codigo));
	} 

	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	*/
	@RequestMapping(value = "/classe/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") long codigo) {

		ClasseModel classeModel = new ClasseModel(this.classeRepository.findById(codigo));

		try {

			this.classeRepository.delete(new ClasseEntity(classeModel));

			return new ResponseModel(1, "Registro excluido com sucesso!");

		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	} 

}
