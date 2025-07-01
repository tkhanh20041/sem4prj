package Sem4.TravelTour.repository.StatisticalRepository;

import Sem4.TravelTour.entity.Tour;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticalRepository extends CrudRepository<Tour, Long> {
    @Query(value = "select sum(amount), month(book_date) from books where year(book_date) = ? and status = 2 group by month(book_date)", nativeQuery = true)
    List<Object[]> getMonthOfYear(int year);

    @Query(value = "select year(book_date) from books group by year(book_date)", nativeQuery = true)
    List<Integer> getYears();

    @Query(value = "select sum(amount) from books where year(book_date) = ? and status = 2", nativeQuery = true)
    Double getRevenueByYear(int year);

    @Query(value = "select sum(p.sold), c.category_name, (p.price*sum(p.sold)-(p.discount)*sum(p.sold)) from categories c\r\n"
            + "join products p on p.category_id = c.category_id\r\n"
            + "group by c.category_name order by sum(p.sold) desc", nativeQuery = true)
    List<Object[]> getCategoryBestSeller();
}
