package Sem4.TravelTour.service.CartService;

import Sem4.TravelTour.entity.Cart;
import Sem4.TravelTour.entity.CartDetail;

import java.util.List;
import java.util.Optional;

public interface CartDetailService {
    void delete (CartDetail cartDetail);
    boolean existsById(Long id);
    List<CartDetail> findByCart(Cart cart);
    Optional<CartDetail> findById(long id);
    CartDetail save(CartDetail cartDetail);
    Optional<CartDetail> deleteById(Long id);
}
