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
public class MeetingFactory implements EventFactory {

    @Override
    public EventDA createEvent(EventView inputEvent) {
        MeetingView meetingView = (MeetingView) inputEvent;
        MeetingDA createdEvent = new MeetingDA();
        Date meetingDate = new Date();

        createdEvent.setName(meetingView.getName());
        createdEvent.setDescription(meetingView.getDescription());
        createdEvent.setMarker(Marker.valueOf(meetingView.getMarker()));
        createdEvent.setLocation(meetingView.getLocation());

        meetingDate.setDate(Integer.parseInt(meetingView.getDay()));
        meetingDate.setMonth(Integer.parseInt(meetingView.getMonth()));
        meetingDate.setYear(Integer.parseInt(meetingView.getYear()));
        meetingDate.setHours(Integer.parseInt(meetingView.getHour()));
        meetingDate.setMinutes(Integer.parseInt(meetingView.getMinutes()));

        return createdEvent;
    }
}
