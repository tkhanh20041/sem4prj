package Sem4.TravelTour.repository.ImageRepository;

import Sem4.TravelTour.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByTour_TourId(Long tourId);
}
