package users.repository;

import users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by benjamin on 16.11.2015.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
