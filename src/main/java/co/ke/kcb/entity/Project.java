package co.ke.kcb.entity;

import co.ke.kcb.dto.ProjectDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PROJECT")
public class Project {

	public Project(ProjectDto dto) {
		this.name = dto.getName();
		this.description = dto.getDescription();
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
}
