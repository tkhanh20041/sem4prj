package Sem4.TravelTour.service.CategoryService;

import Sem4.TravelTour.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();
    boolean existsById(Long id);
    Optional<Category> deleteById(Long id);
    Category save(Category category);
    Optional<Category> findById(Long id);
    List<Category> findAllByIds(List<Long>ids);
}
