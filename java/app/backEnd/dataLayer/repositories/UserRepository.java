package app.backEnd.dataLayer.repositories;


import app.common.models.daModels.userModels.UserDA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDA, Long>{
    UserDA findOneByName(String name);
}
