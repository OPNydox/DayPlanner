package app.backEnd.businessLayer.services.interfaces;

import app.common.models.ViewModels.EventView;
import app.common.models.daModels.eventModels.EventDA;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Ico on 28.12.2016 г..
 */
@Service
public interface EventService {
    void registerEvent(EventView eventToRegister, String owner);

    void updateEvent(EventDA eventToUpdate);

    void deleteEvent(EventDA eventToDelete);

    List<EventDA> getEvents();

    EventDA getEventByName(String name);

    List<EventDA> getEventsByDate(Calendar date);

    List<EventDA> getEventsByMonth(int month, int year);
}
