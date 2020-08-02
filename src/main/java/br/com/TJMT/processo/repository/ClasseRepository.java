package br.com.TJMT.processo.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.TJMT.processo.repository.entity.ClasseEntity;

@org.springframework.stereotype.Repository
public interface ClasseRepository extends Repository<ClasseEntity, Long> {
	
	void save(ClasseEntity classeEntity);
	
	
	void delete(ClasseEntity classeEntity);

	ClasseEntity  findById(Long id);
	
	List<ClasseEntity> findAll();
	
}
