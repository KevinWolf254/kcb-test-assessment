package co.ke.kcb.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import co.ke.kcb.dto.TaskDto;
import co.ke.kcb.entity.Task;
import co.ke.kcb.repository.ProjectRepository;
import co.ke.kcb.repository.TaskRepository;
import co.ke.kcb.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
	private final TaskRepository taskRepository;
	private final ProjectRepository projectRepository;
	
	@Override
	public Task create(Long projectId,@Valid TaskDto dto) {
		
		var project = projectRepository.findById(projectId).orElseThrow(() -> {
			log.info("Project with id {} does not exist", projectId);
			throw new RuntimeException("Project does not exist");
		});
		
		var toSave = new Task(dto, project);
		
		return taskRepository.save(toSave);
	}
	
	@Override
	public Task update(Long taskId,@Valid TaskDto dto) {
		
		var task = taskRepository.findById(taskId).orElseThrow(() -> {
			log.info("Task with id {} does not exist", taskId);
			throw new RuntimeException("Task does not exist");
		});

		task.setTitle(dto.getTitle());
		task.setDueDate(dto.getDueDate());
		task.setDescription(dto.getDescription());
		task.setStatus(dto.getStatus() != null ? dto.getStatus() : task.getStatus());
		
		return taskRepository.save(task);
	}
	
	@Override
	public void delete(Long taskId) {
		
		var task = taskRepository.findById(taskId).orElseThrow(() -> {
			log.info("Task with id {} does not exist", taskId);
			throw new RuntimeException("Task does not exist");
		});
		
		taskRepository.delete(task);
	}

	@Override
	public Page<Task> find(Predicate predicate, Pageable pageable) {
		return taskRepository.findAll(predicate, pageable);
	}
}
