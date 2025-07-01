package Sem4.TravelTour.service.TourService;

import Sem4.TravelTour.entity.TourStatus;

import java.util.List;
import java.util.Optional;

public interface TourStatusService {
    TourStatus save (TourStatus tourStatus);
    boolean existsById(Long id);
    List<TourStatus> findBytourIdAndStatusFalse(Long id);
}
