package com.lschulze.taskmanager.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="task")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Task {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Task(String titulo, String descricao, Status status) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = status;
	}

	public Task(String titulo, String descricao) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = Status.NOVA;
	}

	
		
}
