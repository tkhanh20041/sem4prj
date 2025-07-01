package Sem4.TravelTour.api;

import Sem4.TravelTour.entity.Notification;
import Sem4.TravelTour.service.NotificationService.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/notification")
public class NotificationApi {

    @Autowired
    NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getAll() {
        return ResponseEntity.ok(notificationService.findByOrderByIdDesc());
    }

    @PostMapping
    public ResponseEntity<Notification> post(@RequestBody Notification notification) {
        if (notificationService.existsById(notification.getId())) {
            return ResponseEntity.badRequest().build();
        }
        notification.setTime(new Date());
        notification.setStatus(false);
        return ResponseEntity.ok(notificationService.save(notification));
    }

    @GetMapping("/readed/{id}")
    public ResponseEntity<Notification> put(@PathVariable("id") Long id) {
        if (!notificationService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Notification no = notificationService.getById(id);
        no.setStatus(true);
        return ResponseEntity.ok(notificationService.save(no));
    }

    @GetMapping("/read-all")
    public ResponseEntity<Void> readAll() {
        notificationService.readAll();
        return ResponseEntity.ok().build();
    }
}
