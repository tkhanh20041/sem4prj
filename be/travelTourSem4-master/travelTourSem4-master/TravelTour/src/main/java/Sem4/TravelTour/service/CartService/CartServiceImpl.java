package Sem4.TravelTour.service.CartService;

import Sem4.TravelTour.entity.Cart;
import Sem4.TravelTour.entity.User;
import Sem4.TravelTour.repository.CartRepository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
    @Override
    public boolean existsById(Long id) {
        return cartRepository.existsById(id);
    }
//  @Override
//  public  List<CartDetail> findByCart(Cart cart){
//        return cartRepository.findByCart(cart);
//  }
    @Override
    public Optional<Cart> findById(Long id){
        return cartRepository.findById(id);
    }
    @Override
    public  Cart findByUser(User user){
        return cartRepository.findByUser(user);
    }


}
