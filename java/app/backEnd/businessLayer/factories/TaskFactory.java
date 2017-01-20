package app.backEnd.businessLayer.factories;

import app.backEnd.businessLayer.factories.interfaces.EventFactory;
import app.common.enums.Marker;
import app.common.models.ViewModels.EventView;
import app.common.models.daModels.eventModels.EventDA;
import app.common.models.daModels.eventModels.TaskDA;

/**
 * Created by Ico on 29.12.2016 г..
 */
public class TaskFactory implements EventFactory {

    @Override
    public EventDA createEvent(EventView inputEvent) {
        TaskDA createdEvent = new TaskDA();


        createdEvent.setName(inputEvent.getName());
        createdEvent.setDescription(inputEvent.getDescription());
        createdEvent.setMarker(Marker.valueOf(inputEvent.getMarker()));
        createdEvent.setDateTime(inputEvent.getDate());


        return createdEvent;
    }
}
