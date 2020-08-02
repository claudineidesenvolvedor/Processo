package br.com.TJMT.processo.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.TJMT.processo.model.EnderecoModel;

/*
 * CepWebService.java
 *
 *
 * Classe criada por Fernando Kramerd Souza
 * https://github.com/FernandoKramerdSouza/ViaCep
 *
 */
public class ViaCep {

	// retorna uma lista com o endereço do cep informado (List<String> end =
	// viaCepUtil.ViaVep.buscarCep(cp);)
	public static List<EnderecoModel> buscarCep(String ceep) {

		String cep = ceep.replace("-", "").replace(".", "");

		List<EnderecoModel> lista = new ArrayList<EnderecoModel>();
		EnderecoModel enderecoModel = new EnderecoModel();
		String json;

		try {

			URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			StringBuilder jsonSb = new StringBuilder();

			br.lines().forEach(l -> jsonSb.append(l.trim()));

			json = jsonSb.toString();

			Map<String, String> mapa = new HashMap<>();
			Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(json);
			while (matcher.find()) {
				String[] group = matcher.group().split(":");
				mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
			}

			enderecoModel.setBairro(mapa.get("bairro").toUpperCase());
			enderecoModel.setCidade(mapa.get("localidade").toUpperCase());
			enderecoModel.setEstado(retornaEstado(mapa.get("uf")));
			enderecoModel.setCep(mapa.get("cep"));
			enderecoModel.setLogradouro(mapa.get("logradouro").toUpperCase());

			lista.add(enderecoModel);
		} catch (Exception e) {

		}

		return lista;

	}
/*
	public static List<String> buscarCep(String ceep) {

		String cep = ceep.replace("-", "").replace(".", "");

		List<String> endereco = new ArrayList<String>();

		String json;

		try {

			URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			StringBuilder jsonSb = new StringBuilder();

			br.lines().forEach(l -> jsonSb.append(l.trim()));

			json = jsonSb.toString();

			Map<String, String> mapa = new HashMap<>();
			Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(json);
			while (matcher.find()) {
				String[] group = matcher.group().split(":");
				mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
			}

			endereco.add("BRASIL");
			endereco.add(retornaEstado(mapa.get("uf")));
			endereco.add(mapa.get("uf").toUpperCase());
			endereco.add(mapa.get("localidade").toUpperCase());
			endereco.add(mapa.get("bairro").toUpperCase());
			endereco.add(mapa.get("logradouro").toUpperCase());
			endereco.add(mapa.get("cep"));

		} catch (Exception e) {

		}

		return endereco;

	}*/

	private static String retornaEstado(String uf) {

		String estado;

		switch (uf) {
		case "AC":
			estado = "ACRE";
			break;
		case "AL":
			estado = "ALAGOAS";
			break;
		case "AP":
			estado = "AMAPÁ";
			break;
		case "AM":
			estado = "AMAZONAS";
			break;
		case "BA":
			estado = "BAHIA";
			break;
		case "CE":
			estado = "CEARÁ";
			break;
		case "DF":
			estado = "DISTRITO FEDERAL";
			break;
		case "ES":
			estado = "ESPÍRITO SANTO";
			break;
		case "GO":
			estado = "GOIÁS";
			break;
		case "MA":
			estado = "MARANHÃO";
			break;
		case "MT":
			estado = "MATO GROSSO";
			break;
		case "MS":
			estado = "MATO GROSSO DO SUL";
			break;
		case "MG":
			estado = "MINAS GERAIS";
			break;
		case "PA":
			estado = "PARÁ";
			break;
		case "PB":
			estado = "PARAÍBA";
			break;
		case "PR":
			estado = "PARANÁ";
			break;
		case "PE":
			estado = "PERNAMBUCO";
			break;
		case "PI":
			estado = "PIAUÍ";
			break;
		case "RJ":
			estado = "RIO DE JANEIRO";
			break;
		case "RN":
			estado = "RIO GRANDE DO NORTE";
			break;
		case "RS":
			estado = "RIO GRANDE DO SUL";
			break;
		case "RO":
			estado = "RONDÔNIA";
			break;
		case "RR":
			estado = "RORAIMA";
			break;
		case "SC":
			estado = "SANTA CATARINA";
			break;
		case "SP":
			estado = "SÃO PAULO";
			break;
		case "SE":
			estado = "SERGIPE";
			break;
		case "TO":
			estado = "TOCANTINS";
			break;
		default:
			estado = "Estado não encontrado";
			break;
		}

		return estado;

	}

}
