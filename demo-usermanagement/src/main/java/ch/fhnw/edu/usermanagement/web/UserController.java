package ch.fhnw.edu.usermanagement.web;

import ch.fhnw.edu.usermanagement.model.User;
import ch.fhnw.edu.usermanagement.persistence.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by benjamin on 04.01.2016.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private static final Log log = LogFactory.getLog(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userRepository.findAll();
        log.debug("Found " + users.size() + " users");
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
}
