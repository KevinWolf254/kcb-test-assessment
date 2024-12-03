package co.ke.kcb.controller;

import java.time.LocalDate;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.BooleanBuilder;

import co.ke.kcb.dto.ProjectDto;
import co.ke.kcb.dto.TaskDto;
import co.ke.kcb.entity.QTask;
import co.ke.kcb.entity.Task.TaskStatus;
import co.ke.kcb.service.ProjectService;
import co.ke.kcb.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
	private final ProjectService projectService;
	private final TaskService taskService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid ProjectDto dto) {
		log.info("Project create {}", dto);

		try {
			var response = projectService.create(dto);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error occurred: {}", e);
			return new ResponseEntity<>("Service unavailable, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		try {
			var response = projectService.findAll();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error occurred: {}", e);
			return new ResponseEntity<>("Service unavailable, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<?> findById(@PathVariable Long projectId) {
		try {
			var response = projectService.findById(projectId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error occurred: {}", e);
			return new ResponseEntity<>("Service unavailable, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{projectId}/tasks")
	public ResponseEntity<?> createProjectTask(@PathVariable Long projectId, @RequestBody @Valid TaskDto dto) {
		log.info("Project create {}", dto);

		try {
			var response = taskService.create(projectId, dto);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error occurred: {}", e);
			return new ResponseEntity<>("Service unavailable, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{projectId}/tasks")
	public ResponseEntity<?> findAllPaginated(@PathVariable Long projectId, @RequestParam("page") int page,
			@RequestParam("perPage") int perPage, @RequestParam(value = "status", required = false) TaskStatus status,
			@RequestParam(value = "dueDate", required = false) LocalDate dueDate) {

		try {
			QTask query = QTask.task;
			BooleanBuilder builder = new BooleanBuilder();

			if (status != null) {
				builder.and(query.status.eq(status));
			}

			if (dueDate != null) {
				builder.and(query.dueDate.eq(dueDate));
			}

			builder.and(query.project.id.eq(projectId));

			var response = taskService.find(builder, PageRequest.of(page > 0 ? page - 1 : 0, perPage > 0 ? perPage : 10));

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error occurred: {}", e);
			return new ResponseEntity<>("Service unavailable, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
