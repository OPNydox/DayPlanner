package app.Common.models.daModels.eventModels;

import app.Common.models.daModels.eventModels.EventDA;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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

        return String.format("TO DO:" +
                super.getName() +
                "on" +
                super.getDateTime().toString() +
                "description:" +
                super.getDescription());
    }
}
