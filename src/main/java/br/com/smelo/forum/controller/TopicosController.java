package br.com.smelo.forum.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.smelo.forum.controller.form.TopicoForm;
import br.com.smelo.forum.dto.DetalhesTopicosDto;
import br.com.smelo.forum.dto.TopicosDto;
import br.com.smelo.forum.model.Topico;
import br.com.smelo.forum.repository.CursoRepository;
import br.com.smelo.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicosDto> lista(String nomeCurso) {
		if(nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicosDto.converter(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return TopicosDto.converter(topicos);
		}
	}
	
	@PostMapping
	public ResponseEntity<TopicosDto> cadastrar(@RequestBody @Validated TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicosDto(topico));
	}
	
	@GetMapping("/{id}")
	public TopicosDto detalhar(@PathVariable Long id) {
		Topico topico = topicoRepository.getOne(id);
		return new TopicosDto(topico);
	}
	
	@GetMapping("/detalhes/{id}")
	public DetalhesTopicosDto detalheTopico(@PathVariable Long id) {
		Topico topico = topicoRepository.getOne(id);
		return new DetalhesTopicosDto(topico);
	}

}
