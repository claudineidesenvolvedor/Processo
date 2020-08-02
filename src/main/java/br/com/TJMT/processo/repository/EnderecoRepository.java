package br.com.TJMT.processo.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.TJMT.processo.repository.entity.EnderecoEntity;

@org.springframework.stereotype.Repository
public interface EnderecoRepository extends Repository<EnderecoEntity, Long> {

	void save(EnderecoEntity enderecoEntity);

	void delete(EnderecoEntity enderecoEntity);

	EnderecoEntity findById(Long id);

	List<EnderecoEntity> findAll();

}
