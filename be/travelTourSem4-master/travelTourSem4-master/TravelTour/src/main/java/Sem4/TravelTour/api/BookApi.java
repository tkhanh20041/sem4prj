package Sem4.TravelTour.api;

import Sem4.TravelTour.entity.*;
import Sem4.TravelTour.service.BookService.BookDetailService;
import Sem4.TravelTour.service.BookService.BookService;
import Sem4.TravelTour.service.CartService.CartDetailService;
import Sem4.TravelTour.service.CartService.CartService;
import Sem4.TravelTour.service.TourService.TourService;
import Sem4.TravelTour.service.UserService.UserService;
import Sem4.TravelTour.utils.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("api/books")
public class BookApi {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookDetailService bookDetailService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartDetailService cartDetailService;
    @Autowired
    private TourService tourService;
    @Autowired
    SendMailUtil sendMailUtil;
    @GetMapping
    public ResponseEntity<List<Book>> findAll(){
        return ResponseEntity.ok(bookService.findAllByOrderByBookIdDesc());
    }
    @GetMapping("{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Long id){
        if(!bookService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookService.findById(id).get());
    }
    @GetMapping("/user/{email}")
    public ResponseEntity<List<Book>> getByUser(@PathVariable("email") String email){
        if(!userService.exsitsByEmail(email)){
            return ResponseEntity.notFound().build();
        }
        User user = userService.findByEmail(email).get();
        return ResponseEntity.ok(
                bookService.findByUserOrderByBookIdDesc(user)
        );
    }
    @PostMapping("/{email}/{startDate}/{endDate}")
    public ResponseEntity<Book> checkout (@PathVariable("email") String email,
                                          @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                          @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                          @RequestBody Cart cart){
        if(!userService.exsitsByEmail(email)){
            return ResponseEntity.notFound().build();
        }
        if(!cartService.existsById(cart.getCartId())){
            return ResponseEntity.notFound().build();
        }
        List<CartDetail> items = cartDetailService.findByCart(cart);
        Double amount = 0.0;
        for (CartDetail i : items){
            amount += i.getPrice();
        }
        User user = userService.findByEmail(email).get();
        Book book = bookService.save(new Book (0L, new Date(), amount, cart.getAddress(), cart.getPhone(), 0, startDate, endDate, user));
        for (CartDetail i: items){
            BookDetail bookDetail = new BookDetail(0L, i.getQuantity(), i.getPrice(),startDate, endDate,  i.getTour(), book);
            bookDetailService.save(bookDetail);
        }
        for (CartDetail i : items){
            cartDetailService.delete(i);
        }
        sendMailUtil.sendMailBook(book);
        return ResponseEntity.ok(book);
    }
    @GetMapping("cancel/{bookId}")
    public ResponseEntity<Void> cancel(@PathVariable("bookId") Long id){
        if(!bookService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Book book = bookService.findById(id).get();
        book.setStatus(3);
        bookService.save(book);
        sendMailUtil.sendMailBookCancel(book);
        return ResponseEntity.ok().build();
    }
    @GetMapping("confirm/{bookId}")
    public ResponseEntity<Void> confirm(@PathVariable("bookId") Long id){
        if(!bookService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Book book = bookService.findById(id).get();
        book.setStatus(1);
        bookService.save(book);
        sendMailUtil.sendMailBookConfirm(book);
        return ResponseEntity.ok().build();
    }
    @GetMapping("thanks/{bookId}")
    public ResponseEntity<Void> thanks(@PathVariable("bookId") Long id ){
        if(!bookService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Book book = bookService.findById(id).get();
        book.setStatus(2);
        bookService.save(book);
        sendMailUtil.sendMailBookThanks(book);
        updateTour(book);
        return ResponseEntity.ok().build();
    }
    public void updateTour(Book book){
        List<BookDetail> listBookDetail = bookDetailService.findByBook(book);
        for(BookDetail bookDetail: listBookDetail){
            Tour tour = tourService.findById(bookDetail.getTour().getTourId()).get();
            if(tour != null){
                tour.setQuantity(tour.getQuantity() - bookDetail.getQuantity());
                tour.setSold(tour.getSold() + bookDetail.getQuantity());
                tourService.save(tour);
            }
        }
    }
}
