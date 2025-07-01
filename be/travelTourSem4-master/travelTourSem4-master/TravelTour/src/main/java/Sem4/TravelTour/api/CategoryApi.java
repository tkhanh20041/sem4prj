package Sem4.TravelTour.api;

import Sem4.TravelTour.entity.Category;
import Sem4.TravelTour.service.CategoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/categories")
public class CategoryApi {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<Category> getById(@PathVariable("id") Long id) {
        if (!categoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryService.findById(id).get());
    }
    @PostMapping
    public ResponseEntity<Category> post(@RequestBody Category category) {
        if (categoryService.existsById(category.getCategoryId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(categoryService.save(category));
    }
    @PutMapping("{id}")
    public ResponseEntity<Category> put(@RequestBody Category category, @PathVariable("id") Long id) {
        if (!id.equals(category.getCategoryId())) {
            return ResponseEntity.badRequest().build();
        }
        if (!categoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryService.save(category));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!categoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

