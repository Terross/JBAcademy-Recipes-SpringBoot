package recipes.persistence;

import org.springframework.data.repository.CrudRepository;
import recipes.Entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
