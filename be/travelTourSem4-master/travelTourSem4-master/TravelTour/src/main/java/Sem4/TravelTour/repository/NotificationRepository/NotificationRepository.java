package Sem4.TravelTour.repository.NotificationRepository;

import Sem4.TravelTour.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findByOrderByIdDesc();
    boolean existsById(Long id);
    @Modifying
    @Query(value = "update notification set status = true", nativeQuery = true)
    void readAll();
    Notification save(Notification notification);

    Notification getById(Long id);
}
