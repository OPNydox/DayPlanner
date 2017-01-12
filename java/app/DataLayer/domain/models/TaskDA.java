package app.DataLayer.domain.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by Ico on 27.12.2016 г..
 */
@Entity
@DiscriminatorValue(value = "task")
public class TaskDA extends EventDA {
    public TaskDA() {
        super();
    }

    @Override
    public String toString() {
        Date exportDate = super.getDateTime();

        return String.format("Your task: %s is due to %d-%d-%d at %d:%d", super.getName(), exportDate.getDay(),
                exportDate.getMonth(), exportDate.getYear(), exportDate.getHours(), exportDate.getMinutes());
    }
}
