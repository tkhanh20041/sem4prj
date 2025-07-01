package Sem4.TravelTour.repository.CartRepository;

import Sem4.TravelTour.entity.Cart;
import Sem4.TravelTour.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart save(Cart cart);
    boolean existsById(Long id);
//    List<CartDetail> findByCart(Cart cart);
    Cart findByUser(User user);
    Optional<Cart> findById(Long id);
}
