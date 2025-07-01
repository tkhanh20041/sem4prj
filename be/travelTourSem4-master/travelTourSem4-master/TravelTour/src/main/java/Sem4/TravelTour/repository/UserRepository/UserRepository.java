package Sem4.TravelTour.repository.UserRepository;

import Sem4.TravelTour.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByStatusTrue();
    boolean existsById(Long id);
    Optional<User> findById(Long id);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    User save(User user);
    User findByToken(String token);
}
