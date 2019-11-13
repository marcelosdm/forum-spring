package br.com.smelo.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smelo.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	List<Topico> findByCursoNome(String nomeCurso);

//	Abaixo, um exemplo de query com nome personalizado, sem utilizar 
//	o padrão de nomenclatura do Spring Data
	
//	@Query("SELECT t from Topico t WHERE t.curso.nome = :nomeCurso")
//	List<Topico> buscaCursoNome(@Param("nomeCurso") String nomeCurso);

}
