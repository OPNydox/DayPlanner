package app.BussinessLayer.Services;

import app.BussinessLayer.Factories.FactoryProducer;
import app.BussinessLayer.Factories.Interfaces.EventFactory;
import app.BussinessLayer.Services.Interfaces.EventService;
import app.Common.models.ViewModels.EventView;
import app.DataLayer.domain.models.EventDA;
import app.DataLayer.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Ico on 28.12.2016 г..
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private FactoryProducer factoryProducer = new FactoryProducer();


    private EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.setEventRepository(eventRepository);
    }

    @Override
    public void registerEvent(EventView eventToRegister) {
        EventFactory factory = factoryProducer.createFactory(eventToRegister.getType());

        EventDA entityEvent = factory.createEvent(eventToRegister);

        eventRepository.save(entityEvent);
    }

    @Override
    public void updateEvent(EventDA eventToUpdate) {
        eventRepository.save(eventToUpdate);
    }

    @Override
    public void deleteEvent(EventDA eventToDelete) {
        eventRepository.delete(eventToDelete);
    }

    @Override
    public List<EventDA> getEvents()
    {
        List<EventDA> allEvents = (List<EventDA>) eventRepository.findAll();

        return allEvents;
    }

    @Override
    public EventDA getEventByName(String name) {
        return eventRepository.findOneByName(name);

    }

    private void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
