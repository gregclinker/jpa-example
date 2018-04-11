package hello;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
