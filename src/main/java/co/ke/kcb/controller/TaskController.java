package co.ke.kcb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ke.kcb.dto.TaskDto;
import co.ke.kcb.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
	private final TaskService taskService;

	@PutMapping("/{taskId}")
	public ResponseEntity<?> update(@PathVariable Long taskId, @RequestBody @Valid TaskDto dto) {
		log.info("Task update {} with {}", taskId, dto);

		try {
			var response = taskService.update(taskId, dto);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error occurred: {}", e);
			return new ResponseEntity<>("Service unavailable, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<?> delete(@PathVariable Long taskId) {
		log.info("Task delete {}", taskId);

		try {
			taskService.delete(taskId);
			return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error occurred: {}", e);
			return new ResponseEntity<>("Service unavailable, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
