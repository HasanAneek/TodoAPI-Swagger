package com.exm.todos.controller;

import com.exm.exception.ResponseStatus;
import com.exm.todos.dto.TodoDTO;
import com.exm.todos.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/api")
@RestController
@Tag(name = "Todo", description = "Todo management APIs")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TodoController {

    public static final String CLASS_NAME = TodoController.class.getName();
    private final TodoService todoService;


    @Tag(name = "getAllTodos", description = "Get All Todos APIs")
    @Operation(summary = "Retrieve todos", tags = {"GET"}, description = "Get a todo object by specifying its id. The response is todo object provided back to client.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TodoDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Todo not found.", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/v1/todo/getAll")
    public List<TodoDTO> getAllTodos() {
        return todoService.getAllTodos();
    }

    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = TodoDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
    })
    @Operation(
            summary = "Add a new todo.",
            description = "This api is used to add a new todo in database",
            tags = {"addTodo"}
    )
    //@Tag(name = "addTodo", description = "Add todo details")
    @PostMapping("/v1/todo/add")
    public ResponseEntity<?> addTodo(
            @Parameter(description = "Todo details") @Valid @RequestBody TodoDTO todoDTO
    ) {
        TodoDTO addedTodo = todoService.addTodo(todoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedTodo);
    }

    //@Tag(name = "todoById", description = "Get todo using todo id")
    @Operation(
            summary = "Retrieve a todo by Id",
            description = "Get a todo object by specifying its id.",
            tags = {"todoById", "GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = TodoDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(description = "No todo found"))}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(description = "Server error"))})
    })
    @GetMapping("/v1/todo/{id}")
    public ResponseEntity<?> todoById(
            @Parameter(description = "Todo id", required = true) @PathVariable Long id
    ) {

        log.debug(CLASS_NAME + "::Requested todo id: {} ", id);
        TodoDTO addedTodo = todoService.todoById(id);
        if (addedTodo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(addedTodo);
        } else {
            log.error(CLASS_NAME + "::Todo not available for given id: {}", id);
            throw ResponseStatus.idNotFound.apply(id);
        }
    }

    @Parameters({
            @Parameter(name = "id", description = "todo id to search and update", required = true),
            @Parameter(name = "TodoDTO", description = "Todo details to update")
    })
    @ApiResponses({@ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(description = "Todo details are successfully updated to the DB"), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(description = "Todo not found for the given id"))
            })
    })
    //@Tag(name = "updateTodo", description = "Update the todo details")
    @PutMapping("/v1/todo/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoDTO todoDTO) {
        log.debug(CLASS_NAME + "::Todo details to update-> id: {}, Todo details: {}", id, todoDTO);
        TodoDTO updatedTodo = todoService.updateTodo(id, todoDTO);
        if (updatedTodo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);
        } else {
            log.error(CLASS_NAME + "::Todo not available for given Id: {} ", id);
            throw ResponseStatus.idNotFound.apply(id);
        }
    }

    @Operation(
            summary = "Delete todo by Id",
            description = "This api is used to delete the todo by id",
            tags = {"deleteTodo"}
    )
    @ApiResponses({@ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = TodoDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(description = "Todo not found for given Id"))
            }),
            @ApiResponse(responseCode = "500", content = {
                    @Content(schema = @Schema(defaultValue = "Server error"))
            })
    })
    //@Tag(name = "deleteTodo", description = "Delete the todo details")
    @DeleteMapping("/v1/todo/{id}")
    public ResponseEntity<?> deleteTodo(
            @Parameter(description = "Todo id to delete", required = true) @PathVariable Long id
    ) {
        log.debug(CLASS_NAME + "::Todo id to delete from db: {} ", id);
        String result = todoService.deleteTodo(id);
        if (result != null) {
            log.info(CLASS_NAME + "::EXIT");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            log.error(CLASS_NAME + "::Todo not available for given id: {} ", id);
            throw ResponseStatus.idNotFound.apply(id);
        }
    }


}
