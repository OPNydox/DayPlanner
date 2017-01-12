package app.DataLayer.repositories;

import app.DataLayer.domain.models.EventDA;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ico on 27.12.2016 г..
 */
@Repository
public interface EventRepository extends CrudRepository<EventDA, Long> {
    EventDA findOneByName(String name);

    void deleteByName(String name);

}
