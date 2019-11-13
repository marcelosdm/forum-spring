package br.com.smelo.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smelo.forum.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);


}
