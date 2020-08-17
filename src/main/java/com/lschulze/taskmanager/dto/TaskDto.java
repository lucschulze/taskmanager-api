package com.lschulze.taskmanager.dto;

import java.util.ArrayList;
import java.util.List;

import com.lschulze.taskmanager.model.Status;
import com.lschulze.taskmanager.model.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

	private Long id;
	private String titulo;
	private String descricao;
	private String status;
	
	public static List<TaskDto> converter (List<Task> tasks){
		List<TaskDto> tasksDto = new ArrayList<TaskDto>();
		for (Task task : tasks) {
			tasksDto.add(new TaskDto(task.getId(), task.getTitulo(), task.getDescricao(), task.getStatus().name()));
		}
		return tasksDto;
	}
	
	public TaskDto(Task task) {
		super();
		this.id = task.getId();
		this.titulo = task.getTitulo();
		this.descricao = task.getDescricao();
		this.status = task.getStatus().name();
	}
	
	
}
