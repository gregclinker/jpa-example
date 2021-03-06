package hello;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener implements PreInsertEventListener {

	private static final long serialVersionUID = 1L;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private AuditRepository auditRepository;

	@PostConstruct
	private void init() {
		SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
		EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
		registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(this);
	}

	@Override
	public boolean onPreInsert(PreInsertEvent preInsertEvent) {
		Object entity = preInsertEvent.getEntity();
		if (Customer.class.isInstance(entity)) {
			auditRepository.save(new Audit("inserted " + entity));
		}
		return false;
	}
}