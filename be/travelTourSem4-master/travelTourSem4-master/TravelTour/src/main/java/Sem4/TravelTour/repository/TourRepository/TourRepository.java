package Sem4.TravelTour.repository.TourRepository;

import Sem4.TravelTour.entity.Category;
import Sem4.TravelTour.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Long>
{
    List<Tour> findByStatusTrue();
    @Query(value = "Select t.* From tours t \r\n"
            + "left join rates r on t.tour_id = r.tour_id\r\n"
            + "group by t.tour_id , t.name\r\n"
            + "Order by  avg(r.rating) desc, RAND()", nativeQuery = true)
    List<Tour> findTourRated();
    Tour save(Tour tour);
    List<Tour> findByStatusTrueOrderBySoldDesc();
    Optional<Tour> findById(Long id);
    Tour findByTourIdAndStatusTrue(Long id);
    List<Tour> findByStatusTrueOrderByQuantityDesc();//findByStatusTrueOrderByQuantityDesc()
    Tour getById(Long id);
    @Query("SELECT t FROM Tour t WHERE t.name LIKE %:name% AND t.duration = :duration")
    List<Tour> findByNameAndDuration(@Param("name") String name, @Param("duration") int duration);
    List<Tour> findByCategory(Category category);
    List<Tour> findByStatusTrueOrderByEnteredDateDesc();
    List<Tour> findByLocation_LocationId(Long locationId);
    //find tour by params
    @Query("SELECT td FROM Tour td JOIN td.location ld WHERE LOWER(REPLACE(ld.name, ' ', '')) LIKE LOWER(CONCAT(REPLACE(:locationName, ' ', ''), '%')) AND td.duration = :duration")
    List<Tour> findTourByParams(@Param("locationName") String locationName, @Param("duration") int duration);
    @Query(value = "(Select t.*, avg(r.rating) Rate From tours t\r\n"
            + "left join rates r on t.tour_id = r.tour_id\r\n"
            + "Where (t.category_id = ?) and (t.tour_id != ?)\r\n"
            + "group by t.tour_id , t.name)\r\n"
            + "union\r\n"
            + "(Select t.*, avg(r.rating) Rate From tours t \r\n"
            + "left join rates r on t.tour_id = r.tour_id\r\n"
            + "Where t.category_id != ?\r\n"
            + "group by t.tour_id , t.name)\r\n"
            + "Order by category_id = ? desc, Rate desc", nativeQuery = true)
    List<Tour> findProductSuggest(Long id, Long id2, Long id3, Long id4);

    List<Tour> findByCategoriesIn(List<Category> categories);
    @Query("SELECT t FROM Tour t JOIN Category c ON t.category.categoryId = c.categoryId WHERE c.categoryId = :categoryId")
    List<Tour> findByCategoryOld(@Param("categoryId") Long categoryId);
    @Query("SELECT t FROM Tour t JOIN TourCategory tc ON t.tourId = tc.tour.tourId WHERE tc.category.categoryId = :categoryId")
    List<Tour> findByCategoryNew(@Param("categoryId") Long categoryId);
}
