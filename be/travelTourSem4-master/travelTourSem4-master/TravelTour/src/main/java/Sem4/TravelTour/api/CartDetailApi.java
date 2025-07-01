package Sem4.TravelTour.api;

import Sem4.TravelTour.entity.CartDetail;
import Sem4.TravelTour.entity.Tour;
import Sem4.TravelTour.service.CartService.CartDetailService;
import Sem4.TravelTour.service.CartService.CartService;
import Sem4.TravelTour.service.TourService.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/cartDetail")
public class CartDetailApi {
    @Autowired
    CartService cartService;

   @Autowired
   CartDetailService cartDetailService;

   @Autowired
   TourService tourService;

    @GetMapping("cart/{id}")
    public ResponseEntity<List<CartDetail>> getByCartId(@PathVariable("id") Long id) {
        if (!cartService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDetailService.findByCart(cartService.findById(id).get()));
    }
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<CartDetail> getOne(@PathVariable("id") Long id) {
        if (!cartDetailService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDetailService.findById(id).get());}

    @PostMapping()
    public ResponseEntity<CartDetail> post(@RequestBody CartDetail detail) {
        if (  !cartService.existsById(detail.getCart().getCartId())) {
            return ResponseEntity.notFound().build();
        }

        boolean check = false;
        List<Tour> listT = tourService.findByStatusTrue();
        Tour tour = tourService.findByTourIdAndStatusTrue(detail.getTour().getTourId());
        for (Tour t : listT) {
            if (t.getTourId() == tour.getTourId()) {
                check = true;
            }
        }
        ;
        if (!check) {
            return ResponseEntity.notFound().build();
        }
        List<CartDetail> listD = cartDetailService
                .findByCart(cartService.findById(detail.getCart().getCartId()).get());
        for (CartDetail item : listD) {
            if (item.getTour().getTourId() == detail.getTour().getTourId()) {
                item.setQuantity(item.getQuantity() + 1);
                item.setPrice(item.getPrice() + detail.getPrice());
                return ResponseEntity.ok(cartDetailService.save(item));
            }
        }
        return ResponseEntity.ok(cartDetailService.save(detail));
    }
    @PutMapping()
    public ResponseEntity<CartDetail> put(@RequestBody CartDetail detail) {
        if (!cartService.existsById(detail.getCart().getCartId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDetailService.save(detail));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!cartDetailService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cartDetailService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
