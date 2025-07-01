package Sem4.TravelTour.service.NotificationService;

import Sem4.TravelTour.entity.Notification;

import java.util.List;


public interface NotificationService  {
    List<Notification> findByOrderByIdDesc();
    boolean existsById(Long id);
    Notification save(Notification notification);
    Notification getById(Long id);

    void readAll();

}
