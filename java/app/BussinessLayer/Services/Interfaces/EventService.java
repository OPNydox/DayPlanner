package app.BussinessLayer.Services.Interfaces;

import app.Common.models.ViewModels.EventView;
import app.Common.models.daModels.eventModels.EventDA;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Ico on 28.12.2016 г..
 */
@Service
public interface EventService {
    void registerEvent(EventView eventToRegister);

    void updateEvent(EventDA eventToUpdate);

    void deleteEvent(EventDA eventToDelete);

    List<EventDA> getEvents();

    EventDA getEventByName(String name);

    List<EventDA> getEventsByDate(Calendar date);

    List<EventDA> getEventsByMonth(int month);
}
