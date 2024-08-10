package com.exm.todos.service;

import com.exm.todos.dto.TodoDTO;
import com.exm.todos.entity.Todo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class TodoServiceMapping {

    public static TodoDTO entityToDto(Todo todo) {
        TodoDTO todoDTO = new TodoDTO();
        if (todo != null) {
            BeanUtils.copyProperties(todo, todoDTO);
            return todoDTO;
        }
        return todoDTO;
    }

    public static Todo dtoToEntity(TodoDTO todoDTO) {
        Todo todo = new Todo();
        if (todoDTO != null) {
            BeanUtils.copyProperties(todoDTO, todo);
            return todo;
        }
        return todo;
    }

    public static void createTodoEntity(TodoDTO todoToUpdate, TodoDTO todoDetails) {
        BeanUtils.copyProperties(todoToUpdate, todoDetails);
    }

    public static boolean checkEmptyNullString(String input) {
        return !StringUtils.isEmpty(input) && !StringUtils.isEmpty(input.trim());
    }

}
