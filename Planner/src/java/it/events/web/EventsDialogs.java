package it.events.web;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("eventsDlgs")
@Scope("session")
public class EventsDialogs implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String NEW_EVENT_DLG = "newEventDlg";

	public static final String DATE_SELECT_DLG = "dateSelectDlg";

	public static final String EVENT_SELECT_DLG = "eventSelectDlg";

	public String getNewEventDlg() {
		return NEW_EVENT_DLG;
	}

	public String getDateSelectDlg() {
		return DATE_SELECT_DLG;
	}

	public String getEventSelectDlg() {
		return EVENT_SELECT_DLG;
	}

}
