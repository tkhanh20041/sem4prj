package Sem4.TravelTour.service.BookService;

import Sem4.TravelTour.entity.Book;
import Sem4.TravelTour.entity.BookDetail;
import Sem4.TravelTour.repository.BookRepository.BookDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDetailServiceImpl implements BookDetailService{

    @Autowired
    BookDetailRepository bookDetailRepository;

    @Override
    public BookDetail save(BookDetail bookDetail) {
        return bookDetailRepository.save(bookDetail);
    }
    @Override
    public List<BookDetail> findByBook(Book book) {
        return bookDetailRepository.findByBook(book);
    }
    @Override
    public boolean existsById(Long id) {
        return bookDetailRepository.existsById(id);
    }
    @Override
    public Optional<BookDetail> findById(Long id) {
        return bookDetailRepository.findById(id);
    }
}
