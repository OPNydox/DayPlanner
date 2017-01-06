package app.BussinessLayer.Factories;

import app.BussinessLayer.Factories.Interfaces.EventFactory;
import app.Common.enums.Marker;
import app.Common.models.ViewModels.EventView;
import app.Common.models.ViewModels.MeetingView;
import app.Common.models.ViewModels.TaskView;
import app.DataLayer.domain.models.EventDA;
import app.DataLayer.domain.models.MeetingDA;
import app.DataLayer.domain.models.TaskDA;

import java.util.Date;

/**
 * Created by Ico on 29.12.2016 г..
 */
public class TaskFactory implements EventFactory {

    @Override
    public EventDA createEvent(EventView inputEvent) {
        TaskDA createdEvent = new TaskDA();
        Date meetingDate = new Date();

        createdEvent.setName(inputEvent.getName());
        createdEvent.setDescription(inputEvent.getDescription());
        createdEvent.setMarker(Marker.valueOf(inputEvent.getMarker()));


        meetingDate.setDate(Integer.parseInt(inputEvent.getDay()));
        meetingDate.setMonth(Integer.parseInt(inputEvent.getMonth()));
        meetingDate.setYear(Integer.parseInt(inputEvent.getYear()));
        meetingDate.setHours(Integer.parseInt(inputEvent.getHour()));
        meetingDate.setMinutes(Integer.parseInt(inputEvent.getMinutes()));

        return createdEvent;
    }
}
