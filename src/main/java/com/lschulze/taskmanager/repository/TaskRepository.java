package com.lschulze.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lschulze.taskmanager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByTitulo(String titulo);
}
