package app.BussinessLayer.Factories;

import app.BussinessLayer.Factories.Interfaces.EventFactory;
import app.Common.enums.Marker;
import app.Common.models.ViewModels.EventView;
import app.Common.models.ViewModels.MeetingView;
import app.Common.models.ViewModels.TaskView;
import app.DataLayer.domain.models.EventDA;
import app.DataLayer.domain.models.MeetingDA;
import app.DataLayer.domain.models.TaskDA;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Ico on 29.12.2016 г..
 */
@Component
public class MeetingFactory implements EventFactory {

    @Override
    public EventDA createEvent(EventView inputEvent) {
        MeetingView meetingView = (MeetingView) inputEvent;
        MeetingDA createdEvent = new MeetingDA();
        Calendar meetingDate = new GregorianCalendar();

        createdEvent.setName(meetingView.getName());
        createdEvent.setDescription(meetingView.getDescription());
        createdEvent.setMarker(Marker.valueOf(meetingView.getMarker()));
        createdEvent.setLocation(meetingView.getLocation());
        createdEvent.setDateTime(meetingDate);

        return createdEvent;
    }
}
