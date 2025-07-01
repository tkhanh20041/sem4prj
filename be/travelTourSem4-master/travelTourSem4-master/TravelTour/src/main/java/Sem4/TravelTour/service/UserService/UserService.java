package Sem4.TravelTour.service.UserService;

import Sem4.TravelTour.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    boolean existsById(Long id);
    Optional<User> findById(Long id);
    boolean exsitsByEmail(String email);
    Optional<User> findByEmail(String email);
    User save(User user);
    User findByToken(String token);

}
