package co.ke.kcb.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import co.ke.kcb.entity.Task;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long>, CrudRepository<Task, Long>, 
QuerydslPredicateExecutor<Task> {

}
