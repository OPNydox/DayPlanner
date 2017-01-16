package app.BussinessLayer.CalendarElements.Days.Interfaces;

import app.Common.models.daModels.eventModels.EventDA;

import java.util.Set;

/**
 * Created by Ico on 3.1.2017 г..
 */
public interface Day {
    void add(EventDA eventToAdd);

    void add(Set<EventDA> eventsToAdd);

    void delete(EventDA eventToDelete);

    Set<EventDA> getEvents();


}
