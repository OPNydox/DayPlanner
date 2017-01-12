package app.BussinessLayer.CalendarElements.Days;

import app.BussinessLayer.CalendarElements.Days.Interfaces.Day;
import app.DataLayer.domain.models.EventDA;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ico on 3.1.2017 г..
 */
public class DayImpl implements Day{

    private Set<EventDA> events;

    public DayImpl() {
        events = new HashSet<>();
    }

    @Override
    public void add(EventDA eventToAdd) {
        events.add(eventToAdd);
    }

    @Override
    public void add(Set<EventDA> eventsToAdd) {
        events.addAll(eventsToAdd);
    }

    @Override
    public void delete(EventDA eventToDelete) {
        for (EventDA event : events) {
            if (eventToDelete.getName().equals(event.getName())){
                events.remove(event);
            }
        }
    }

    @Override
    public Set<EventDA> getEvents() {
        Set<EventDA> eventsCopy = new HashSet<>(events);

        return eventsCopy;
    }
}
