package Sem4.TravelTour.service.BookService;

import Sem4.TravelTour.entity.Book;
import Sem4.TravelTour.entity.User;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAllByOrderByBookIdDesc();
    boolean existsById(Long id);
    Optional<Book> findById(Long id);
//    boolean existsByEmail(String email);
    List<Book> findByUserOrderByBookIdDesc(User user);
    Book save(Book book);
    List<Book> findByStatus(int status);
}
