package Sem4.TravelTour.service.NotificationService;

import Sem4.TravelTour.entity.Notification;
import Sem4.TravelTour.repository.NotificationRepository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public List<Notification> findByOrderByIdDesc() {
        return notificationRepository.findByOrderByIdDesc();
    }

    @Override
    public boolean existsById(Long id) {
        return notificationRepository.existsById(id);
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification getById(Long id) {
        return notificationRepository.getById(id);
    }

    @Override
    @Transactional
    public void readAll() {
        notificationRepository.readAll();
    }
}