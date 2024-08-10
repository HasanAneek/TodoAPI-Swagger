
package com.exm.todos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({ "title", "description", "isCompleted" })
@Schema(description = "Todo dto information")
public class TodoDTO {

	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "todo id", example = "123133")
	@JsonProperty("id")
	private Long id;

	@Schema(description = "This field describe the todos name", example = "Need doc appointment")
	@NotBlank(message = "Title name shouldn't be null or empty")
	@JsonProperty("title")
	private String title;

	@Schema(description = "This field describe the todos description", example = "Need doc appointment on 10th Jan 2022 at 10:00 AM.")
	@NotNull(message = "Description shouldn't be null")
    @NotEmpty(message = "Description shouldn't be empty")
	@JsonProperty("description")
	private String description;

	@Schema(description = "This field describe the todos status", example = "true")
	@JsonProperty("isCompleted")
	private boolean isCompleted;


//	@Size(max = 15, min = 10, message = "Invalid mobile number")
//	@Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number")
//	@Schema(description = "This field describe the todo's contact person number", example = "9988776655")
//	@Digits(message = "Invalid mobile number", fraction = 0, integer = 10)
//	@JsonProperty("mobileNumber")
//	private Long mobileNumber;



}
