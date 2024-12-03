package co.ke.kcb.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import co.ke.kcb.entity.Task.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDto {
	
	@NotBlank(message = "Task title is required")
	private String title;
	
	private String description;
		
	private TaskStatus status;
	
	@NotNull(message = "Task due date is required")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dueDate;

}
