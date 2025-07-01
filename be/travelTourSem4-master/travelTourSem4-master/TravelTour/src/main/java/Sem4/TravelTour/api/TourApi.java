package Sem4.TravelTour.api;

import Sem4.TravelTour.dto.CloseTourDto;
import Sem4.TravelTour.dto.FindTourByLocationDto;
import Sem4.TravelTour.entity.Category;
import Sem4.TravelTour.entity.Image;
import Sem4.TravelTour.entity.Tour;
import Sem4.TravelTour.entity.TourStatus;
import Sem4.TravelTour.service.CategoryService.CategoryService;
import Sem4.TravelTour.service.ImageService.ImageService;
import Sem4.TravelTour.service.TourService.TourService;
import Sem4.TravelTour.service.TourService.TourStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("api/tours")
public class TourApi {
    @Autowired
    private TourService tourService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TourStatusService tourStatusService;
    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<List<Tour>> getAll() {
        return ResponseEntity.ok(tourService.getAll());
    }
    @GetMapping("bestseller")
    public ResponseEntity<List<Tour>> getBestSeller() {
        return ResponseEntity.ok(tourService.getBestSeller());
    }
    @GetMapping("rated")
    public ResponseEntity<List<Tour>> getRated() {
        return ResponseEntity.ok(tourService.getRate());
    }
    @GetMapping("{id}")
    public ResponseEntity<Tour> getById(@PathVariable("id") Long id) {
        if (!tourService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tourService.findById(id).get());
    }
    @GetMapping("suggest/{categoryId}/{productId}")
    public ResponseEntity<List<Tour>> suggest(@PathVariable("categoryId") Long categoryId,
                                                 @PathVariable("productId") Long productId) {
        return ResponseEntity.ok(tourService.findProductSuggest(categoryId, productId, categoryId, categoryId));
    }
    @PostMapping
    public ResponseEntity<Tour> post(@RequestBody Tour tours) {
//        if (tourService.existsById(tour.getTourId())) {
//            return ResponseEntity.badRequest().build();
//        }
        return ResponseEntity.ok(tourService.save(tours));
    }
    @PutMapping("{id}")
    public ResponseEntity<Tour> put(@PathVariable("id") Long id, @RequestBody Tour tour   ) {
        if (!id.equals(tour.getTourId())){
            return ResponseEntity.notFound().build();
        }
        if (!tourService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tourService.save(tour));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!tourService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Tour t = tourService.findById(id).get();
        t.setStatus(false);
        tourService.save(t);
        return ResponseEntity.ok().build();
    }
    //find tour by name and duration
    @GetMapping("search")
    public ResponseEntity<List<Tour>> search(@RequestParam("name") String name, @RequestParam("duration") int duration) {
        return ResponseEntity.ok(tourService.findByNameAndDuration(name, duration));
    }
//    @GetMapping("category/{id}")
//    public ResponseEntity<List<Tour>> getByCategory(@PathVariable("id") Long id) {
//        if(!categoryService.existsById(id)){
//            return ResponseEntity.notFound().build();
//        }
//        Category c = categoryService.findById(id).get();
//        return ResponseEntity.ok(tourService.findByCategory(c));
//    }

    @GetMapping("category/{id}")
    public ResponseEntity<List<Tour>> getByCategory(@PathVariable("id") Long id) {
        if(!categoryService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Long c = categoryService.findById(id).get().getCategoryId();
        return ResponseEntity.ok(tourService.findByCategoryIncludingOldAndNew(c));
    }
    @GetMapping("latest")
    public ResponseEntity<List<Tour>> getLatest() {
        return ResponseEntity.ok(tourService.findByStatusTrueOrderByEnteredDateDesc());
    }
    @PostMapping("closeTour")
    public ResponseEntity<TourStatus> closeTour(@RequestBody CloseTourDto closeTourDto){
        Long tourIdInput = closeTourDto.getTourId();
        if (!tourService.existsById(tourIdInput)) {
            return ResponseEntity.notFound().build();
        }
        TourStatus ts = new TourStatus();
        ts.setTourId(tourIdInput);
        ts.setFromDate(closeTourDto.getFromDate());
        ts.setToDate(closeTourDto.getToDate());
        ts.setStatus(false);

        return ResponseEntity.ok(tourStatusService.save(ts));
    }
    @GetMapping("getClosedTourById/{id}")
    public ResponseEntity<List<TourStatus>> getClosedTourById(@PathVariable("id") Long id){
        if(!tourService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        List<TourStatus> ts = tourStatusService.findBytourIdAndStatusFalse(id);
        if (ts.isEmpty()) {
            // Optionally, add a message to the response body if needed
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(ts);
    }
    @GetMapping("getTourImages/{id}")
    public ResponseEntity<List<String>> getTourImages(@PathVariable("id") Long id) {
        if (!tourService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<Image> images = imageService.findByTour_TourId(id);
        List<String> imageUrls = images.stream().map(Image::getImageUrl).collect(Collectors.toList());
        return ResponseEntity.ok(imageUrls);
    }
    @GetMapping("findTourByLocationId/{id}")
    public ResponseEntity<List<Tour>> findTourByLocationId(@PathVariable("id") Long id){
        return ResponseEntity.ok(tourService.findTourByLocationId(id));
    }
    @PostMapping("findTourByParams")
    public ResponseEntity<List<Tour>> findTourByParams(@RequestBody FindTourByLocationDto dto){
        return ResponseEntity.ok(tourService.findTourByParams(dto));
    }
    @PostMapping("addTourImage/{id}")
    public ResponseEntity<List<String>> addTourImage(@PathVariable Long id, @RequestBody List<String> imageUrls) {
        if (tourService.existsById(id)) {
            // Existing tour: Add images to the specified tour
            List<Image> images = imageService.addImagesToTour(id, imageUrls);

            List<String> imageUrlsList = images.stream()
                    .map(Image::getImageUrl)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(imageUrlsList);
        } else {
            // New tour (id == 0): Return a message indicating images can't be added
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Arrays.asList("Cannot add image URLs to a non-existent tour. Please create the tour first."));
        }
    }
    @GetMapping("categories")
    public ResponseEntity<List<Tour>> getByCategories(@RequestParam("ids") List<Long> ids) {
        List<Category> categories = categoryService.findAllByIds(ids);

        if (categories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tourService.findByCategories(categories));
    }
}
