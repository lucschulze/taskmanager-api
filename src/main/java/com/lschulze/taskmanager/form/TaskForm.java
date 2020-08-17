package com.lschulze.taskmanager.form;

import com.lschulze.taskmanager.model.Status;
import com.lschulze.taskmanager.model.Task;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TaskForm {
	
	private String titulo;
	private String descricao;
	
	public Task converter() {
		return new Task(titulo, descricao);
	}

}
