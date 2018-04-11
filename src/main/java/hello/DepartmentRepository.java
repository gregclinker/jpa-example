package hello;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
