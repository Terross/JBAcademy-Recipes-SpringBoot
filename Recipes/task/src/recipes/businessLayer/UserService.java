package recipes.businessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import recipes.Entity.User;
import recipes.persistence.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public HttpStatus addUser(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresentOrElse(
                value -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                },
                () -> {
                    user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
                    userRepository.save(user);
                }
        );

        return HttpStatus.OK;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST)
        );
    }
}
