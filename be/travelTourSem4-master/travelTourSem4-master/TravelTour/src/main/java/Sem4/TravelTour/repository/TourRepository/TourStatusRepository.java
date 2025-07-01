package Sem4.TravelTour.repository.TourRepository;

import Sem4.TravelTour.entity.TourStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TourStatusRepository extends JpaRepository<TourStatus, Long> {
    TourStatus save (TourStatus tourStatus);
    List<TourStatus> findBytourIdAndStatusFalse(Long tourId);
}
