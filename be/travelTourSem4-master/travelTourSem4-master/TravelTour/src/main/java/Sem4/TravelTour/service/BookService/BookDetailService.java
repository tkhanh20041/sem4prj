package Sem4.TravelTour.service.BookService;

import Sem4.TravelTour.entity.Book;
import Sem4.TravelTour.entity.BookDetail;

import java.util.List;
import java.util.Optional;

public interface BookDetailService {
    BookDetail save(BookDetail bookDetail);
    List<BookDetail> findByBook(Book book);
    boolean existsById(Long id);
    Optional<BookDetail> findById(Long id);
}
