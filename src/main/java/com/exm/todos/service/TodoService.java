package com.exm.todos.service;

import com.exm.todos.dto.TodoDTO;
import com.exm.todos.entity.Todo;
import com.exm.todos.repo.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TodoService {

    public static final String CLASS_NAME = TodoService.class.getName();
    private final TodoRepository todoRepository;

    public List<TodoDTO> getAllTodos() {
        List<TodoDTO> allTodos = new ArrayList<>();
        try {
            todoRepository.findAll().forEach(
                    todo -> allTodos.add(TodoServiceMapping.entityToDto(todo))
            );

        } catch (Exception e) {
            log.error(CLASS_NAME + "::Exception occurred:" + e.getMessage());
        }
        return allTodos;
    }

    public TodoDTO addTodo(TodoDTO todoDto) {
        Todo todo = null;
        try {
            todo = todoRepository.save(TodoServiceMapping.dtoToEntity(todoDto));
        } catch (Exception e) {
            log.error(CLASS_NAME + "::Exception occurred:" + e.getMessage());
        }
        return TodoServiceMapping.entityToDto(todo);
    }


    public TodoDTO todoById(Long id) {
        TodoDTO todoDTO = null;
        try {
            Optional<Todo> todoOptional = todoRepository.findById(id);
            if (todoOptional.isPresent()) {
                todoDTO = TodoServiceMapping.entityToDto(todoOptional.get());
                log.debug(CLASS_NAME + "::Response is: {}", todoDTO);
            }
        } catch (Exception e) {
            log.error(CLASS_NAME + "::Exception occurred:" + e.getMessage());
        }
        return todoDTO;
    }


    public String deleteTodo(Long id) {
        String result = null;
        try {
            TodoDTO todoDetails = todoById(id);
            if (todoDetails != null) {
                todoRepository.deleteById(id);
                result = "Todo successfully deleted.";
                log.debug(CLASS_NAME + "::Deleted status response is: {}", result);
            }
        } catch (Exception e) {
            log.error(CLASS_NAME + "::Exception occurred:" + e.getMessage());
        }
        return result;
    }

    public TodoDTO updateTodo(Long id, TodoDTO todoToUpdate) {
        TodoDTO updatedTodo = null;
        try {
            TodoDTO todoDetails = todoById(id);
            if (todoDetails != null) {
                todoToUpdate.setId(id);
                Todo todo = todoRepository.save(TodoServiceMapping.dtoToEntity(todoToUpdate));
                updatedTodo = TodoServiceMapping.entityToDto(todo);
                log.debug(CLASS_NAME + "::Response is: {}", updatedTodo);
            }
        } catch (Exception e) {
            log.error(CLASS_NAME + "::Exception occurred:" + e.getMessage());
        }
        return updatedTodo;
    }


}
