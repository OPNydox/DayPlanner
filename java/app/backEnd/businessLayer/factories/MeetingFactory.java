package app.backEnd.businessLayer.factories;

import app.backEnd.businessLayer.factories.interfaces.EventFactory;
import app.common.enums.Marker;
import app.common.models.ViewModels.EventView;
import app.common.models.ViewModels.MeetingView;
import app.common.models.daModels.eventModels.EventDA;
import app.common.models.daModels.eventModels.MeetingDA;
import org.springframework.stereotype.Component;

import java.util.Calendar;
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
