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

import br.com.TJMT.processo.model.EnderecoModel;
import br.com.TJMT.processo.model.ResponseModel;
import br.com.TJMT.processo.repository.EnderecoRepository;
import br.com.TJMT.processo.repository.entity.EnderecoEntity;
import br.com.TJMT.processo.util.ViaCep;

@RestController
@RequestMapping("/service")
public class EnnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	/**
	 * SALVAR UM NOVO REGISTRO
	 * 
	 * @param enderecoModel
	 * @return
	 */
	@RequestMapping(value = "/endereco", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody EnderecoModel enderecoModel) {

		try {

			this.enderecoRepository.save(new EnderecoEntity(enderecoModel));

			return new ResponseModel(1, "Registro salvo com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * ATUALIZAR O REGISTRO DE UM ENDEREÇO
	 * 
	 * @param enderecoModel
	 * @return
	 */
	@RequestMapping(value = "/endereco", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody EnderecoModel enderecoModel) {

		try {

			this.enderecoRepository.save(new EnderecoEntity(enderecoModel));

			return new ResponseModel(1, "Registro atualizado com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * CONSULTAR TODOS OS ENDEREÇOS
	 * 
	 * @return listaModel
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/endereco", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<EnderecoModel> consultar() {
		final List<EnderecoModel> listaModel = new ArrayList();

		List<EnderecoEntity> listaEntity = this.enderecoRepository.findAll();

		listaEntity.forEach(entity -> {
			listaModel.add(new EnderecoModel(entity));
		});
		return listaModel;
	}

	/**
	 * CONSULTAR TODOS OS ENDEREÇOS PELO CEP
	 * 
	 * @return listaModel
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/endereco/buscacep/{cep}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<EnderecoModel> consultar(@PathVariable("cep") String ceep) {
		List<EnderecoModel> listaModel = new ArrayList();

		ViaCep viaCep = new ViaCep();

		listaModel = ViaCep.buscarCep(ceep);

		return listaModel;
	}

	/**
	 * BUSCAR UM ENDEREÇO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/endereco/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody EnderecoModel buscar(@PathVariable("codigo") Long codigo) {

		return new EnderecoModel(this.enderecoRepository.findById(codigo));
	}

	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/endereco/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") long codigo) {

		EnderecoModel enderecoModel = new EnderecoModel(this.enderecoRepository.findById(codigo));

		try {

			this.enderecoRepository.delete(new EnderecoEntity(enderecoModel));

			return new ResponseModel(1, "Registro excluido com sucesso!");

		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}

}
