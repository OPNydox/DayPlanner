package app.domain.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Ico on 27.12.2016 г..
 */
@Entity
@DiscriminatorValue(value = "task")
public class Task extends Event {
    public Task() {
        super();
    }
}
