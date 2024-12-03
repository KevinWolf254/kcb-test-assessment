package co.ke.kcb.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.ke.kcb.entity.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	Optional<Project> findByName(String name);

}
