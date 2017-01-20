package app.common.models.daModels.eventModels;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Calendar;

/**
 * Created by Ico on 27.12.2016 г..
 */
@Entity
@DiscriminatorValue(value = "meeting")
public class MeetingDA extends EventDA {
    @Column(name = "place")
    private String location;

    public MeetingDA() {
        super();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {

        Calendar meetingDate = super.getDateTime();

        return String.format(meetingDate.getTime().toString() +
                " " +
                "Meeting:" +
                " |" +
                super.getName() +
                "| Description: " +
                super.getDescription());
    }
}
