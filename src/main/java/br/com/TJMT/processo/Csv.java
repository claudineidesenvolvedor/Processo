package br.com.TJMT.processo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.TJMT.processo.model.ClasseModel;
import br.com.TJMT.processo.repository.ClasseRepository;
import br.com.TJMT.processo.repository.entity.ClasseEntity;

@SpringBootApplication
@ComponentScan
public class Csv {

	@Autowired
	private static ClasseRepository classeRepository;

	private static final String CAMINHO = "C:/Users/Cuiaba/Downloads/Teste_Datainfo_TJMT/Teste Datainfo TJMT/TABELA_DE_CLASSES_ATIVAS.csv";

	public static void main(String[] args) throws IOException {
		// SpringApplication.run(Csv.class, args);
		// ApplicationContext ctx = new AnnotationConfigApplicationContext(Csv.class);
		SpringApplication.run(Csv.class, args).getBean(ClasseRepository.class);

		// classeRepository = ctx.getBean(ClasseRepository.class);

		List<ClasseModel> listaModel = new ArrayList();
		/*
		 * Leitura do arquivo
		 */
		System.out.println("============ Leitura do arquivo: \n");

		Files.lines(Paths.get(CAMINHO)).forEach(System.out::println);

		System.out.println("\n============ Leitura do arquivo sem cabecalho: \n");

		/*
		 * Leitura do arquivo sem cabecalho
		 */
		Files.lines(Paths.get(CAMINHO)).skip(1).forEach(System.out::println);

		/*
		 * Conversão em um objeto do Tipo User
		 */
		System.out.println("\n============ Conversão em um objeto do Tipo User: \n");

		Files.lines(Paths.get(CAMINHO)).skip(1).map(line -> line.split(",")).forEach(str -> {
			listaModel.add(new ClasseModel(Long.parseLong(str[0]), str[1], str[2], str[3], str[4]));
		});
		System.out.println("\n============ final Conversão em um objeto do Tipo User: \n");

		for (ClasseModel classeModel : listaModel) {
			classeRepository.save(new ClasseEntity(classeModel));
		}
	}

}
