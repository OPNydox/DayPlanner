package app.DataLayer.repositories;


import app.Common.models.daModels.userModels.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
}
