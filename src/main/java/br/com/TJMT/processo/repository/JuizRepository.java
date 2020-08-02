package br.com.TJMT.processo.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.TJMT.processo.repository.entity.JuizEntity;

@org.springframework.stereotype.Repository
public interface JuizRepository extends Repository<JuizEntity, Long> {

	void save(JuizEntity juizEntity);

	void delete(JuizEntity juizEntity);

	JuizEntity findById(Long id);

	List<JuizEntity> findAll();	

}
