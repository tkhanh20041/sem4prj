package Sem4.TravelTour.api;

import Sem4.TravelTour.repository.UserRepository.UserRepository;
import Sem4.TravelTour.service.MailService.SendMailService;
import Sem4.TravelTour.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/send-mail")
public class SendMailApi {
    @Autowired
    SendMailService sendMail;

    @Autowired
    UserService userService;

    @PostMapping("/otp")
    public ResponseEntity<Integer> sendOpt(@RequestBody String email) {
        int random_otp = (int) Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
        if (userService.exsitsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        sendMailOtp(email, random_otp, "Xác nhận tài khoản!");
        return ResponseEntity.ok(random_otp);
    }

    // sendmail
    public void sendMailOtp(String email, int Otp, String title) {
        String body = "<div>\r\n" + "        <h3>Mã OTP của bạn là: <span style=\"color:red; font-weight: bold;\">"
                + Otp + "</span></h3>\r\n" + "    </div>";
        sendMail.queue(email, title, body);
    }
}
