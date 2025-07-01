package Sem4.TravelTour.service.BookService;

import Sem4.TravelTour.entity.Book;
import Sem4.TravelTour.entity.User;
import Sem4.TravelTour.repository.BookRepository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;
    @Override
    public List<Book> findAllByOrderByBookIdDesc() {
        return bookRepository.findAllByOrderByBookIdDesc();
    }
    @Override
    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }
    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
//    @Override
//    public boolean existsByEmail(String email) {
//        return  bookRepository.existsByEmail(email);
//    }
    @Override
    public List<Book> findByUserOrderByBookIdDesc(User user) {
        return bookRepository.findByUserOrderByBookIdDesc(user);
    }
    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public  List<Book> findByStatus(int status){
        return bookRepository.findByStatus(status);
    }
}
