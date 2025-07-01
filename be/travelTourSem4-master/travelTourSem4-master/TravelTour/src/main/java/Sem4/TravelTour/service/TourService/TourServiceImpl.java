package Sem4.TravelTour.service.TourService;

import Sem4.TravelTour.dto.FindTourByLocationDto;
import Sem4.TravelTour.entity.Category;
import Sem4.TravelTour.entity.Tour;
import Sem4.TravelTour.repository.TourRepository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository tourRepository;


    @Override
    public boolean existsById(Long id) {
        return tourRepository.existsById(id);
    }
    @Override
    public List<Tour> getAll() {
        return tourRepository.findByStatusTrue();
    }
    @Override
    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }
    @Override
    public List<Tour>  getBestSeller() {
        return tourRepository.findByStatusTrueOrderBySoldDesc();
    }
    @Override
    public List<Tour> getRate() {
        return tourRepository.findTourRated();
    }
    @Override
    public Optional<Tour> findById(Long id) {
        return tourRepository.findById(id);
    }
    @Override
    public List<Tour> findByStatusTrue() {
        return tourRepository.findByStatusTrue();
    }
    @Override
    public Tour findByTourIdAndStatusTrue(Long id) {return  tourRepository.findByTourIdAndStatusTrue(id);}
    @Override
    public List<Tour> findByStatusTrueOrderByQuantityDesc() {return tourRepository.findByStatusTrueOrderByQuantityDesc();}
    @Override
    public Tour getById(Long id) {
        return tourRepository.getById(id);
    }
    @Override
    public List<Tour> findByNameAndDuration(String name, int duration) {
        return tourRepository.findByNameAndDuration(name,duration);
    }
    @Override
    public List<Tour> findByCategory(Category category) {
        return tourRepository.findByCategory(category);
    }
    @Override
    public List<Tour> findByStatusTrueOrderByEnteredDateDesc() {
        return tourRepository.findByStatusTrueOrderByEnteredDateDesc();
    }
    @Override
    public List<Tour> findTourByLocationId(Long locationId) {
        return tourRepository.findByLocation_LocationId(locationId);
    }
    @Override
    public List<Tour> findTourByParams(FindTourByLocationDto dto) {
        String locationName = dto.getLocationName();
//        Date startDate = dto.getStartDate();
        int duration = dto.getDuration();
        //format startDate to int yyyymmdd
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//        String stringDate = dateFormat.format(startDate);
//        int formattedDate = Integer.parseInt(stringDate);

        return tourRepository.findTourByParams(locationName, duration);
    }
    @Override
    public  List<Tour> findProductSuggest(Long id, Long id2, Long id3, Long id4){
        return tourRepository.findProductSuggest(id,id2,id3,id4);
    }
    @Override
    public List<Tour> findByCategories(List<Category> categories) {
        return tourRepository.findByCategoriesIn(categories);
    }
    @Override
    public List<Tour> findByCategoryIncludingOldAndNew(Long categoryId) {
        // Tìm từ bảng tour_categories (mới)
        List<Tour> toursFromNewTable = tourRepository.findByCategoryNew(categoryId);

        // Tìm từ bảng category_tour (cũ)
        List<Tour> toursFromOldTable = tourRepository.findByCategoryOld(categoryId);

        // Kết hợp các kết quả và loại bỏ trùng lặp
        Set<Tour> allTours = new HashSet<>();
        allTours.addAll(toursFromNewTable);
        allTours.addAll(toursFromOldTable);

        return new ArrayList<>(allTours);
    }
}
