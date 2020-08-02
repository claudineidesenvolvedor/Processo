package br.com.TJMT.processo.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.TJMT.processo.repository.entity.ProcessoEntity;

@org.springframework.stereotype.Repository
public interface ProcessoRepository extends Repository<ProcessoEntity, Long> {

	ProcessoEntity save(ProcessoEntity processoEntity);

	ProcessoEntity findById(Long id);

	List<ProcessoEntity> findAll();

}
