package Sem4.TravelTour.service.ImageService;

import Sem4.TravelTour.entity.Image;

import java.util.List;

public interface ImageService {
    List<Image> findByTour_TourId(Long tourId);
    List<Image> addImagesToTour(Long id, List<String> imageUrls);
}
