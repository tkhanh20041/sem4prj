package Sem4.TravelTour.service.FavoriteService;

import Sem4.TravelTour.entity.Favorite;
import Sem4.TravelTour.entity.Tour;
import Sem4.TravelTour.entity.User;

import java.util.List;

public interface FavoriteService {
    List<Favorite> findByUser(User user);
    Integer countByTour(Tour tour);
    Favorite findByTourAndUser(Tour tour, User user);
    Favorite save(Favorite favorite);
    boolean existsById(Long id);
    void deleteById(Long id);
}
