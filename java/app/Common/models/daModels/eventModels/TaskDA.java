package app.Common.models.daModels.eventModels;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Calendar;

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

        Calendar taskDate = super.getDateTime();

        return String.format(taskDate.getTime().toString() +
                " Task: " +
                "|" +
                super.getName() +
                "|" +
                " Description: " +
                super.getDescription());
    }
}
