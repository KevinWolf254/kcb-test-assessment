package co.ke.kcb.service.impl;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.ke.kcb.dto.ProjectDto;
import co.ke.kcb.dto.TaskDto;
import co.ke.kcb.entity.Project;
import co.ke.kcb.entity.Task;
import co.ke.kcb.repository.ProjectRepository;
import co.ke.kcb.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
	@Mock
	TaskRepository taskRepository;
	@Mock
	ProjectRepository projectRepository;

	@InjectMocks
	private TaskServiceImpl underTest;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void create_ReturnsTask_WhenSuccessful() {
		// given
		Long projectId = 1l;

		var dto = new TaskDto();
		dto.setTitle("Do Test Assessmet");
		dto.setDescription("Assessment for kcb");
		dto.setDueDate(LocalDate.now().plusDays(2));
		
		var task = new Task(dto, null);
		task.setId(1l);
		
		var projectDto = new ProjectDto();
		projectDto.setName("Test");
		
		var project = new Project(projectDto);
		project.setId(projectId);
		
		// when
		when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));
		when(taskRepository.save(task)).thenReturn(task);
		
		// then
		var result = underTest.create(projectId, dto);
		
		assertNotNull(result);

	}

}
