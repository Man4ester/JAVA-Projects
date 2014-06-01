package it.events.blogic.interfaces;

import it.events.blogic.models.Events;

import java.util.Date;
import java.util.List;

public interface IEventsService {

	public List<Events> loadEventsByUserId(int userId);

	public List<Events> loadByDate(int userId, Date start, Date end);

}
