package br.com.TJMT.processo;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@SpringBootApplication
@ComponentScan
public class ProcessoApplication {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProcessoApplication.class, args);
		//DistribuicaoService service = new DistribuicaoService();
		// service.start();

	}

	@Bean(name = "hibernateProperties")
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("javax.persistence.jdbc.driver", "org.postgresql.Driver");
		properties.setProperty("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/processo");
		properties.setProperty("javax.persistence.jdbc.user", "postgres");
		properties.setProperty("javax.persistence.jdbc.password", "68302187");
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		return properties;
	}

	// não precisa definir o datasource programaticamente
	// pode ser pelo properties
	// o bean DataSource pode ser esperado como parâmetro para essa configuração
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			@Qualifier("hibernateProperties") Properties properties) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPackagesToScan(new String[] { "br.com.TJMT.processo.repository.entity" });
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setJpaProperties(properties);
		return emf;
	}
	
}
