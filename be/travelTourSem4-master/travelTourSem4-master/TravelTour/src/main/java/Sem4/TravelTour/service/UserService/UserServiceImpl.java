package Sem4.TravelTour.service.UserService;

import Sem4.TravelTour.entity.User;
import Sem4.TravelTour.repository.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAll() {
        return userRepository.findByStatusTrue();
    }
    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public boolean exsitsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }
}
