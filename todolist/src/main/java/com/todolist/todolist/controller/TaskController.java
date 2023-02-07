package com.todolist.todolist.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.todolist.todolist.model.Task;
import com.todolist.todolist.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
@Tag(name = "Task", description="To Do List")
public class TaskController {
	
TaskService taskservice;
	
	@PostMapping("/tasks")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Criar uma nova tarefa")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso",
					content = @Content),
			@ApiResponse(responseCode = "500", description = "Houve um erro ao criar uma nova tarefa",
					content = @Content) })
	public Task createTask(@RequestBody Task task) {
		
		return taskservice.createTask(task);
	}
	
	@GetMapping("/tasks")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary="Visualizar todas as tarefas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tarefas listadas com sucesso!",
					content = @Content),
			@ApiResponse(responseCode = "500", description = "Houve um erro ao buscar uma nova tarefa",
					content = @Content) })
	public List<Task> getAllTasks(){
		return taskservice.listAllTasks();
	}
	
	@GetMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary="Buscar uma tarefa por Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tarefa listada com sucesso!",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Não foi localizada nenhuma tarefa com esse id",
					content = @Content) })
	public ResponseEntity<Task> getTaskById(@PathVariable (value = "id") Long id){
		return taskservice.findTaskById(id);
	}
	
	@PutMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary="Atualizar uma tarefa (id)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso!",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Houve um erro ao atualizar a tarefa",
					content = @Content) })
	public ResponseEntity<Task> getTaskById(@PathVariable (value = "id") Long id, @RequestBody Task task){
		return taskservice.updateTaskById(task, id);
	}
	
	@DeleteMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary="Deletar uma tarefa (Id)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Tarefa excluida com sucesso!",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Não foi possível excluir a tarefa",
					content = @Content) })
	public ResponseEntity<Object> deleteTaskById(@PathVariable (value = "id") Long id){
		return taskservice.deleteById(id);
	}
	
}
