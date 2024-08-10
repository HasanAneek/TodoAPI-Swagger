# Todo List RESTful API with Spring Boot

## Project Overview

This project is a RESTful API built using Spring Boot for managing a simple todo list. The API allows users to perform basic CRUD (Create, Read, Update, Delete) operations on todo items. It includes the following features:

- Endpoints for creating, retrieving, updating, and deleting todo items.
- Integration with an in-memory H2 database for data storage.
- API documentation generated using Swagger.

## Requirements

### Endpoints

The API provides the following endpoints:

- Create a new todo item**: `POST /api/todos`
- Retrieve a list of all todo items**: `GET /api/todos`
- Retrieve a single todo item by its ID**: `GET /api/todos/{id}`
- Update an existing todo item**: `PUT /api/todos/{id}`
- Delete an existing todo item**: `DELETE /api/todos/{id}`

### Error Handling

The API implements appropriate error handling to manage invalid requests or operations, ensuring that clients receive meaningful error messages.

### Technology Stack

- **Spring Boot**: To build and run the RESTful API.
- **Spring MVC**: For handling HTTP requests and responses.
- **H2 Database**: An in-memory database to store todo items.
- **Swagger**: For generating API documentation and providing a UI to explore API endpoints.

## Getting Started

### Prerequisites

To run this project, you will need:

- **Java 17 or higher**
- **Maven** (for dependency management)

### Access the API:
The API will be running at http://localhost:8080/api/todos.

### Access Swagger Documentation:
Navigate to http://localhost:8080/swagger-ui.html to view and interact with the API documentation.
Database
This project uses an in-memory H2 database, which is automatically configured. You can access the H2 console at http://localhost:8080/h2-console (username: sa, password: password) for inspecting the database.

### API Documentation
Swagger is used to provide documentation and an interactive UI for the API. You can explore and test the API endpoints via the Swagger UI at http://localhost:8080/swagger-ui.html.

  
