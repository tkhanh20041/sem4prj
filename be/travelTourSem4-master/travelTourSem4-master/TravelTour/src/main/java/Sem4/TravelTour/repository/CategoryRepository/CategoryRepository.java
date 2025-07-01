package Sem4.TravelTour.repository.CategoryRepository;

import Sem4.TravelTour.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);
    Category save(Category category);
    Optional<Category> deleteByCategoryId(Long id);

}
