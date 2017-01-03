package app.BussinessLayer.Services.Interfaces;

import app.Common.models.ViewModels.EventView;
import app.DataLayer.domain.models.EventDA;

import java.util.List;

/**
 * Created by Ico on 28.12.2016 г..
 */
public interface EventService {
    void registerEvent(EventView eventToRegister);

    void updateEvent(EventView eventToUpdate);

    void deleteEvent(EventView eventToDelete);

    List<EventDA> getEvents();

    EventDA getEventByName();

    EventDA popEvent(String eventName);

}
