package app.domain.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Ico on 27.12.2016 г..
 */
@Entity
@DiscriminatorValue(value = "meeting")
public class Meeting extends Event{
    public Meeting() {
        super();
    }
}
