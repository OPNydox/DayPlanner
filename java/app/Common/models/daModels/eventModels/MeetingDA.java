package app.Common.models.daModels.eventModels;

import app.Common.models.daModels.eventModels.EventDA;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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

        return String.format("Meeting" +
                super.getName() +
                "on" +
                super.getDateTime().toString() +
                " at " +
                this.getLocation() +
                "description: " +
                super.getDescription());
    }
}
