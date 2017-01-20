package app.backEnd.businessLayer.services;

import app.backEnd.businessLayer.services.interfaces.UserService;
import app.backEnd.dataLayer.repositories.UserRepository;
import app.common.models.ViewModels.UserView;
import app.common.models.daModels.userModels.UserDA;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDA getUser(UserView userToGet) {
        return repository.findOneByName(userToGet.getUserName());
    }

    @Override
    public void registerUser(UserView userToRegister) {
        UserDA newUser = new UserDA();

        newUser.setName(userToRegister.getUserName());

        newUser.setPassword(userToRegister.getPassword().hashCode());

        repository.save(newUser);
    }

    @Override
    public UserDA getUser(String userName) {
        return repository.findOneByName(userName);
    }

}
