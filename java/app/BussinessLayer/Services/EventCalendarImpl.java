package app.BussinessLayer.Services;

import app.BussinessLayer.CalendarElements.CustomCalendar.Interfaces.EventCalendar;
import app.BussinessLayer.CalendarElements.Days.Interfaces.Day;
import app.BussinessLayer.CalendarElements.Months.Interfaces.Month;
import app.BussinessLayer.CalendarElements.Years.Interfaces.Year;
import app.BussinessLayer.Services.Interfaces.EventCalendarService;
import app.BussinessLayer.Services.Interfaces.EventService;
import app.DataLayer.domain.models.EventDA;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by Ico on 3.1.2017 г..
 */
public class EventCalendarImpl implements EventCalendarService{
    @Autowired
    private EventService eventService;

    @Autowired
    private EventCalendar calendar;


    private Day getEventDay(Date date){
        Year eventYear = calendar.getYear(date.getYear());

        Month eventMonth = eventYear.getMonth(date.getMonth());

        Day eventDay = eventMonth.getDay(date.getDay());

        return eventDay;
    }

    @Override
    public void bootCalendar() {
        List<EventDA> eventsToAdd = eventService.getEvents();

        Set<EventDA> setOfEvents = new HashSet<>(eventsToAdd);

        add(setOfEvents);
    }

    @Override
    public List<EventDA> getEventsAt(Date date) {
        Day eventDay = this.getEventDay(date);

        List eventList = new ArrayList();
        eventDay.add(eventDay.getEvents());

        return eventList;
    }

    @Override
    public void add(EventDA eventToAdd) {
        Day eventDay = this.getEventDay(eventToAdd.getDateTime());

        eventDay.add(eventToAdd);
    }

    @Override
    public void add(Set<EventDA> eventsToAdd) {
        for (EventDA event : eventsToAdd) {
            this.add(event);
        }
    }
}
