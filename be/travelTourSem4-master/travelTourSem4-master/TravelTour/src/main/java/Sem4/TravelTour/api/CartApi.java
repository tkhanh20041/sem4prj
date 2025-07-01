package Sem4.TravelTour.api;

import Sem4.TravelTour.entity.Cart;
import Sem4.TravelTour.service.CartService.CartService;
import Sem4.TravelTour.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/cart")
public class CartApi {
    @Autowired
    private CartService cartService;
    @Autowired
    UserService userService;
    @GetMapping("/user/{email}")
    public ResponseEntity<Cart> getCartUser(@PathVariable("email") String email) {
        if (!userService.exsitsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartService.findByUser(userService.findByEmail(email).get()));
    }
    @PutMapping("/user/{email}")
    public ResponseEntity<Cart> putCartUser(@PathVariable("email") String email, @RequestBody Cart cart) {
        if (!userService.exsitsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartService.save(cart));
    }
}
