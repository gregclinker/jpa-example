package hello;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface AuditRepository extends CrudRepository<Audit, Long> {
}
