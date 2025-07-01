package Sem4.TravelTour.service.CartService;

import Sem4.TravelTour.entity.Cart;
import Sem4.TravelTour.entity.User;

import java.util.Optional;

public interface CartService {
    Cart save(Cart cart);
    boolean existsById(Long id);
//    List<CartDetail> findByCart(Cart cart);
    Cart findByUser(User user);

    Optional<Cart> findById(Long id);
}
