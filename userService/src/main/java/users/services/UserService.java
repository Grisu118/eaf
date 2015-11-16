package users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import users.model.User;
import users.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class UserService implements IUserService {

    @Autowired
    UserRepository repo;

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return repo.findOne(id);
    }
}
