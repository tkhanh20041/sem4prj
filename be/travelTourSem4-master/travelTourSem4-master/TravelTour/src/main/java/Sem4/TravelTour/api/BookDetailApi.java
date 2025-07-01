package Sem4.TravelTour.api;

import Sem4.TravelTour.entity.BookDetail;
import Sem4.TravelTour.service.BookService.BookDetailService;
import Sem4.TravelTour.service.BookService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/bookDetail")
public class BookDetailApi {
    @Autowired
    BookService bookService;

    @Autowired
    BookDetailService bookDetailService;
    @GetMapping("/book/{id}")
    public ResponseEntity<List<BookDetail>> getByOrder(@PathVariable("id") Long id) {
        if (!bookService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookDetailService.findByBook(bookService.findById(id).get()));
    }
}
