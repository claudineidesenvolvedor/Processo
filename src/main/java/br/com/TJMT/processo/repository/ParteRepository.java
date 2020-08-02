package br.com.TJMT.processo.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.TJMT.processo.repository.entity.ParteEntity;

@org.springframework.stereotype.Repository
public interface ParteRepository extends Repository<ParteEntity, Long> {

	void save(ParteEntity parteEntity);

	void delete(ParteEntity parteEntity);

	ParteEntity findById(Long id);

	List<ParteEntity> findAll();

}
