package Sem4.TravelTour.service.RateService;

import Sem4.TravelTour.entity.BookDetail;
import Sem4.TravelTour.entity.Rate;
import Sem4.TravelTour.entity.Tour;

import java.util.List;

public interface RateService {
    List<Rate> findAllByOrderByIdDesc();
    Rate findByBookDetail(BookDetail bookDetail);
    List<Rate> findByTourOrderByIdDesc(Tour tour);
    Rate save(Rate rate);
}
