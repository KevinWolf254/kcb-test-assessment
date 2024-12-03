package co.ke.kcb.entity;

import java.time.LocalDate;

import co.ke.kcb.dto.TaskDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TASK")
public class Task {

	public Task(TaskDto dto, Project project) {
		this.title = dto.getTitle();
		this.description = dto.getDescription();
		this.status = dto.getStatus() != null ? dto.getStatus() : TaskStatus.TO_DO;
		this.project = project;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus status = TaskStatus.TO_DO;
	
	@Column(name = "due_date")
	private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="project_id", nullable = false)
	private Project project;
	
	public enum TaskStatus {
		TO_DO, IN_PRORESS, DONE
	}
}
