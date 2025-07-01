package Sem4.TravelTour.service.CategoryService;

import Sem4.TravelTour.entity.Category;
import Sem4.TravelTour.repository.CategoryRepository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public Optional<Category> deleteById(Long id) {return categoryRepository.deleteByCategoryId(id);}

    @Override
    public List<Category> findAllByIds(List<Long> ids) {
        return categoryRepository.findAllById(ids);
    }
}
