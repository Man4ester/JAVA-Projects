package it.events.web;

import it.events.blogic.interfaces.IEventsService;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("eventsBean")
@Scope("session")
public class EventsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(EventsBean.class);

	private ScheduleModel lazyEventModel;

	@Autowired
	private IEventsService eventsService;

	@PostConstruct
	public void init() {
		logger.info("init");
		lazyEventModel = new LazyScheduleModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void loadEvents(Date start, Date end) {
				addEvent(new DefaultScheduleEvent("Title", new Date(),
						new Date()));
			}

		};
	}

	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	public void setLazyEventModel(ScheduleModel lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
	}

}
