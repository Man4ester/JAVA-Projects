package it.events.blogic.services;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.events.blogic.interfaces.IEventsService;
import it.events.blogic.models.Events;

@Repository
@Transactional
public class EventsService implements IEventsService {

	private EntityManager entityManager;

	@Resource
	private ApplicationContext ctx;

	@PersistenceContext(unitName = "plannerCentralEntityManagerFactory")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Events> loadEventsByUserId(int userId) {
		if (userId == 0) {
			throw new RuntimeException("userId can't be 0");
		}
		String sql = "SELECT * FROM events WHERE userId=:userId";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("userId", userId);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Events> loadByDate(int userId, Date start, Date end) {
		String sql = "SELECt * FROM events WHERE (userId=:user AND startDate=:start AND endDate=:end)";
		Query query = entityManager.createNativeQuery(sql, Events.class);
		query.setParameter("user", userId);
		query.setParameter("start", start);
		query.setParameter("end", end);

		return query.getResultList();
	}

}
