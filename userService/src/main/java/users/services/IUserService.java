package users.services;

import users.model.User;

import java.util.List;

/**
 * Created by benjamin on 16.11.2015.
 */
public interface IUserService {

    List<User> getAllUsers();

    User getUserById(Long id);

}
