package Sem4.TravelTour.service.ImageService;

import Sem4.TravelTour.entity.Image;
import Sem4.TravelTour.entity.Tour;
import Sem4.TravelTour.repository.ImageRepository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public List<Image> findByTour_TourId(Long tourId) {
        List<Image> images = imageRepository.findByTour_TourId(tourId);
        return images;
    }
    @Override
    public List<Image> addImagesToTour(Long id, List<String> imageUrls) {
        List<Image> images = imageUrls.stream()
                .map(url -> {
                    Image image = new Image();
                    image.setImageUrl(url);
                    image.setDescription("Description: Image of Tour" + id);
                    //get current timestamp
                    String current_timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    image.setCreatedAt(current_timestamp);
                    image.setUpdatedAt(current_timestamp);

                    // Associate the image with the tour
                    Tour tour = new Tour();
                    tour.setTourId(id);
                    image.setTour(tour);

                    return image;
                })
                .collect(Collectors.toList());

        // Save all images at once
        return imageRepository.saveAll(images);
    }
}
