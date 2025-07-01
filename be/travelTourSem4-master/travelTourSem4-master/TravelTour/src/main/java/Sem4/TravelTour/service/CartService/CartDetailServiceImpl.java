package Sem4.TravelTour.service.CartService;

import Sem4.TravelTour.entity.Cart;
import Sem4.TravelTour.entity.CartDetail;
import Sem4.TravelTour.repository.CartRepository.CartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartDetailServiceImpl implements CartDetailService{

    @Autowired
    CartDetailRepository cartDetailRepository;
    @Override
    public void delete(CartDetail cartDetail) {
        cartDetailRepository.delete(cartDetail);
    }
    @Override
    public boolean existsById(Long id) {
        return cartDetailRepository.existsById(id);
    }
    @Override
    public List<CartDetail> findByCart(Cart cart) {return cartDetailRepository.findByCart(cart);}
    @Override
    public Optional<CartDetail> findById(long id){return cartDetailRepository.findById(id);}
    @Override
    public CartDetail save(CartDetail cartDetail) {return cartDetailRepository.save(cartDetail);}
    @Transactional
    public Optional<CartDetail> deleteById(Long id) {return cartDetailRepository.deleteByCartDetailId(id);}

}
