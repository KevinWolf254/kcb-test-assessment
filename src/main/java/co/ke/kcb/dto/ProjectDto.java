package co.ke.kcb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDto {
	
	@NotBlank(message = "Project name is required")
	private String name;
	
	private String description;
}
