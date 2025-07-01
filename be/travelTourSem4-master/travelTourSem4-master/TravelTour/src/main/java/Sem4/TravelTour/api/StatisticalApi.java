package Sem4.TravelTour.api;

import Sem4.TravelTour.dto.CategoryBestSeller;
import Sem4.TravelTour.dto.Statistical;
import Sem4.TravelTour.entity.Book;
import Sem4.TravelTour.entity.Tour;
import Sem4.TravelTour.service.BookService.BookService;
import Sem4.TravelTour.service.StatisticalService.StatisticalService;
import Sem4.TravelTour.service.TourService.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/statistical")
public class StatisticalApi {
    @Autowired
    StatisticalService statisticalService;

    @Autowired
    BookService bookService;

    @Autowired
    TourService tourService;

    @GetMapping("{year}")
    public ResponseEntity<List<Statistical>> getStatisticalYear(@PathVariable("year") int year) {
        List<Object[]> list = statisticalService.getMonthOfYear(year);
        List<Statistical> listSta = new ArrayList<>();
        List<Statistical> listReal = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Statistical sta = new Statistical((int) list.get(i)[1], null, (Double) list.get(i)[0], 0);
            listSta.add(sta);
        }
        for (int i = 1; i < 13; i++) {
            Statistical sta = new Statistical(i, null, 0.0, 0);
            for (int y = 0; y < listSta.size(); y++) {
                if (listSta.get(y).getMonth() == i) {
                    listReal.remove(sta);
                    listReal.add(listSta.get(y));
                    break;
                } else {
                    listReal.remove(sta);
                    listReal.add(sta);
                }
            }
        }
        return ResponseEntity.ok(listReal);
    }
    @GetMapping("/revenue/year/{year}")
    public ResponseEntity<Double> getRevenueByYear(@PathVariable("year") int year) {
        return ResponseEntity.ok(statisticalService.getRevenueByYear(year));
    }

    @GetMapping("/get-all-book-success")
    public ResponseEntity<List<Book>> getAllBookSuccess() {
        return ResponseEntity.ok(bookService.findByStatus(2));
    }

    @GetMapping("/get-category-seller")
    public ResponseEntity<List<CategoryBestSeller>> getCategoryBestSeller() {
        List<Object[]> list = statisticalService.getCategoryBestSeller();
        List<CategoryBestSeller> listCategoryBestSeller = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CategoryBestSeller categoryBestSeller = new CategoryBestSeller(String.valueOf(list.get(i)[1]),
                    Integer.valueOf(String.valueOf(list.get(i)[0])), Double.valueOf(String.valueOf(list.get(i)[2])));
            listCategoryBestSeller.add(categoryBestSeller);
        }
        return ResponseEntity.ok(listCategoryBestSeller);
    }

    @GetMapping("/get-inventory")
    public ResponseEntity<List<Tour>> getInventory() {
        return ResponseEntity.ok(tourService.findByStatusTrueOrderByQuantityDesc());
    }
    @GetMapping("/countYear")
    public ResponseEntity<List<Integer>> getYears() {
        return ResponseEntity.ok(statisticalService.getYears());
    }
}
