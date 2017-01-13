package app.DataLayer.domain.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

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
        Date exportDate = super.getDateTime();
        return String.format("Meeting on %d-%d-%d at %d:%d at %s", exportDate.getDay(), exportDate.getMonth(),
                exportDate.getYear(), exportDate.getHours(), exportDate.getMinutes(), this.getLocation());
    }
}
