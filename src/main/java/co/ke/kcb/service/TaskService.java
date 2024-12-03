package co.ke.kcb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import co.ke.kcb.dto.TaskDto;
import co.ke.kcb.entity.Task;
import jakarta.validation.Valid;

public interface TaskService {

	Task create(Long projectId, @Valid TaskDto dto);

	Task update(Long taskId, @Valid TaskDto dto);

	void delete(Long taskId);

	Page<Task> find(Predicate predicate, Pageable pageable);
}
