package ch.fhnw.edu.usermanagement.persistence;

import ch.fhnw.edu.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by benjamin on 04.01.2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    List<User> findAll();
}
