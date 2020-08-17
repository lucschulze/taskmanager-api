package com.lschulze.taskmanager.form;

import com.lschulze.taskmanager.model.Status;
import com.lschulze.taskmanager.model.Task;
import com.lschulze.taskmanager.repository.TaskRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AtualizacaoTaskForm {

	private String titulo;
	private String descricao;
	private Status status;
	
	public Task atualizar(Long id, TaskRepository taskRepository) {
		Task task = taskRepository.getOne(id);
		task.setTitulo(this.getTitulo());
		task.setDescricao(this.getDescricao());
		task.setStatus(this.getStatus());
		return task;
	}
	
}
