package Sem4.TravelTour.repository.BookRepository;

import Sem4.TravelTour.entity.Book;
import Sem4.TravelTour.entity.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail, Long> {
    BookDetail save(BookDetail bookDetail);
    List<BookDetail> findByBook(Book book);
    boolean existsById(Long id);
    Optional<BookDetail> findById(Long id);
}
