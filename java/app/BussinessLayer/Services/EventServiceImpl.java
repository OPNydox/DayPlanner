package app.BussinessLayer.Services;

import app.BussinessLayer.Factories.FactoryProducer;
import app.BussinessLayer.Factories.Interfaces.EventFactory;
import app.BussinessLayer.Services.Interfaces.EventService;
import app.Common.models.ViewModels.EventView;
import app.DataLayer.domain.models.EventDA;
import app.DataLayer.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Ico on 28.12.2016 г..
 */
public class EventServiceImpl implements EventService {

    @Autowired
    private FactoryProducer factoryProducer;

    @Autowired
    private EventRepository eventRepository;


    @Override
    public void registerEvent(EventView eventToRegister) {
        EventFactory factory = factoryProducer.createFactory(eventToRegister.getType());

        EventDA entityEvent = factory.createEvent(eventToRegister);

        eventRepository.save(entityEvent);
    }

    @Override
    public void updateEvent(EventView eventToUpdate) {
        EventFactory factory = factoryProducer.createFactory(eventToUpdate.getType());

        EventDA eventDA = factory.createEvent(eventToUpdate);

        eventRepository.save(eventDA);
    }

    @Override
    public void deleteEvent(EventView eventToDelete) {
        eventRepository.deleteByName(eventToDelete.getName());
    }

    @Override
    public List<EventDA> getEvents()
    {
        List<EventDA> allEvents = (List<EventDA>) eventRepository.findAll();

        return allEvents;
    }

    @Override
    public EventDA getEventByName() {
        return null;
    }

    @Override
    public EventDA popEvent(String eventName) {
        EventDA poppedEvent = eventRepository.findOneByName(eventName);

        eventRepository.deleteByName(eventName);

        return poppedEvent;
    }


}
