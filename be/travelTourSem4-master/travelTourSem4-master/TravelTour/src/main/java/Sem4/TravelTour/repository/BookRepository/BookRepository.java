package Sem4.TravelTour.repository.BookRepository;

import Sem4.TravelTour.entity.Book;
import Sem4.TravelTour.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    //find all books order by id desc
    List<Book> findAllByOrderByBookIdDesc();
    boolean existsById(Long id);
    Optional<Book> findById(Long id);
    List<Book> findByUserOrderByBookIdDesc(User user);
    Book save(Book book);
    List<Book> findByStatus(int status);
}
