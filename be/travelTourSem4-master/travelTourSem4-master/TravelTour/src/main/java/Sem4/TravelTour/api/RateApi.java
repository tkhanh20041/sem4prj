package Sem4.TravelTour.api;

import Sem4.TravelTour.entity.Rate;
import Sem4.TravelTour.service.BookService.BookDetailService;
import Sem4.TravelTour.service.BookService.BookService;
import Sem4.TravelTour.service.RateService.RateService;
import Sem4.TravelTour.service.TourService.TourService;
import Sem4.TravelTour.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/rates")
public class RateApi {
    @Autowired
    RateService rateService;
    @Autowired
    BookService bookService;
    @Autowired
    BookDetailService bookDetailService;
    @Autowired
    TourService tourService;
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<Rate>> findAll() {
        return ResponseEntity.ok(rateService.findAllByOrderByIdDesc());
    }

    @GetMapping("{orderDetailId}")
    public ResponseEntity<Rate> findById(@PathVariable Long orderDetailId) {
        if (!bookDetailService.existsById(orderDetailId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rateService.findByBookDetail(bookDetailService.findById(orderDetailId).get()));
    }

    @GetMapping("/tour/{id}")
    public ResponseEntity<List<Rate>> findByProduct(@PathVariable("id") Long id) {
        if (!tourService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rateService.findByTourOrderByIdDesc(tourService.findById(id).get()));
    }
    @PostMapping
    public ResponseEntity<Rate> post(@RequestBody Rate rate) {
        if (!userService.existsById(rate.getUser().getUserId())) {
            return ResponseEntity.notFound().build();
        }
        if (!tourService.existsById(rate.getTour().getTourId())) {
            return ResponseEntity.notFound().build();
        }
        if (!bookDetailService.existsById(rate.getBookDetail().getBookDetailId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rateService.save(rate));
    }

}
