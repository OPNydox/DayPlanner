package app.backEnd.businessLayer.services.interfaces;


import app.common.models.ViewModels.UserView;
import app.common.models.daModels.userModels.UserDA;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDA getUser(UserView userToGet);

    void registerUser(UserView userToRegister);

    UserDA getUser(String userName);
}
