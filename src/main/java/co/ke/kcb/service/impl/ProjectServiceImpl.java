package co.ke.kcb.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.ke.kcb.dto.ProjectDto;
import co.ke.kcb.entity.Project;
import co.ke.kcb.repository.ProjectRepository;
import co.ke.kcb.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	private final ProjectRepository projectRepository;
	
	@Override
	public Project create(ProjectDto dto) {
		
		var result = projectRepository.findByName(dto.getName());
		
		if(result.isPresent()) {
			log.info("Project with name {} already exists", dto.getName());
			throw new RuntimeException("Project already exists");
		}
		
		var toSave = new Project(dto);
		
		return projectRepository.save(toSave);
	}

	@Override
	public List<Project> findAll() {
		Iterator<Project> iterator = projectRepository.findAll().iterator();
		List<Project> result = new ArrayList<Project>();
		
		while(iterator.hasNext()) {
			result.add(iterator.next());
		}
		
		return result;
	}
	
	@Override
	public Optional<Project> findById(Long projectId) {
		return projectRepository.findById(projectId);
	}
}
