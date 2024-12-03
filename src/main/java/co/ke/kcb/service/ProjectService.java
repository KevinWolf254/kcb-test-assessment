package co.ke.kcb.service;

import java.util.List;
import java.util.Optional;

import co.ke.kcb.dto.ProjectDto;
import co.ke.kcb.entity.Project;

public interface ProjectService {

	Project create(ProjectDto dto);

	List<Project> findAll();

	Optional<Project> findById(Long projectId);
}
