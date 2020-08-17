package com.lschulze.taskmanager.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lschulze.taskmanager.dto.TaskDto;
import com.lschulze.taskmanager.form.AtualizacaoTaskForm;
import com.lschulze.taskmanager.form.TaskForm;
import com.lschulze.taskmanager.model.Task;
import com.lschulze.taskmanager.repository.TaskRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping
	public List<TaskDto> lista(HttpServletResponse response) {
		List<Task> tasks = taskRepository.findAll();
		response.addHeader("Access-Control-Expose-Headers", "X-Total-Count");
		response.addHeader("X-Total-Count", "10");
		return TaskDto.converter(tasks);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TaskDto> register(@RequestBody @Valid TaskForm form, UriComponentsBuilder uriBuilder) {
		Task task = form.converter();
		taskRepository.save(task);		
		URI uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).body(new TaskDto(task));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskDto> detalhar(@PathVariable Long id) {
		Optional<Task> category = taskRepository.findById(id);
		if(category.isPresent()) {
			return ResponseEntity.ok(new TaskDto(category.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TaskDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTaskForm form){
		Optional<Task> optional = taskRepository.findById(id);
		if(optional.isPresent()) {
			Task task = form.atualizar(id, taskRepository);
			System.out.println(task);
			return ResponseEntity.ok(new TaskDto(task));
		}
		return ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id)
	{
		Optional<Task> optional = taskRepository.findById(id);
		if(optional.isPresent()) {
			taskRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
