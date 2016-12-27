package app.repositories;

import app.domain.models.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ico on 27.12.2016 г..
 */
@Repository
public interface EventRepository extends CrudRepository {
}
