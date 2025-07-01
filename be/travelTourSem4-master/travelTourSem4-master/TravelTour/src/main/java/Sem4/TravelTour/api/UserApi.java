package Sem4.TravelTour.api;

import Sem4.TravelTour.config.JwtUtils;
import Sem4.TravelTour.dto.JwtResponse;
import Sem4.TravelTour.dto.LoginRequest;
import Sem4.TravelTour.dto.MessageResponse;
import Sem4.TravelTour.dto.SignupRequest;
import Sem4.TravelTour.entity.AppRole;
import Sem4.TravelTour.entity.Cart;
import Sem4.TravelTour.entity.User;
import Sem4.TravelTour.service.CartService.CartService;
import Sem4.TravelTour.service.MailService.SendMailService;
import Sem4.TravelTour.service.UserService.UserDetailsImpl;
import Sem4.TravelTour.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")

public class UserApi {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    SendMailService sendMailService;
    private final UserService userService;
    private final CartService cartService;
    @Autowired
    public UserApi(UserService userService, CartService cartService, SendMailService sendMailService) {
        this.userService = userService;
        this.cartService = cartService;
        this.sendMailService = sendMailService;
    }
    @GetMapping("{getAll}")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<User> getOne(@PathVariable("id") Long id){
        if(!userService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.findById(id).get());
    }
    @GetMapping("email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable("email") String email){
        if(userService.exsitsByEmail(email)){
            return ResponseEntity.ok(userService.findByEmail(email).get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        if(userService.exsitsByEmail(user.getEmail())){
            return ResponseEntity.notFound().build();
        }
//        if(userService.existsById(user.getUserId())){
//            return ResponseEntity.badRequest().build();
//        }
        Set<AppRole> roles = new HashSet<>();
        roles.add(new AppRole(2, null)); //1 is user 2 is admin
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setToken(jwtUtils.doGenerateToken(user.getEmail()));
        User u = userService.save(user);
        System.out.println("User input: " + user.toString());
        Cart c = new Cart(0L, 0.0, u.getAddress(), u.getPhone(), u);
        cartService.save(c);
        return ResponseEntity.ok(u);
    }
    @PutMapping("{id}")
    public ResponseEntity<User> put(@PathVariable("id") Long id, @RequestBody User user) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        if (!id.equals(user.getUserId())) {
            return ResponseEntity.badRequest().build();
        }

        User temp = userService.findById(id).get();

        if (!user.getPassword().equals(temp.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        Set<AppRole> roles = new HashSet<>();
        roles.add(new AppRole(1, null));

        user.setRoles(roles);
        return ResponseEntity.ok(userService.save(user));
    }
    @PutMapping("admin/{id}")
    public ResponseEntity<User> putAdmin(@PathVariable("id") Long id, @RequestBody User user) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        if (!id.equals(user.getUserId())) {
            return ResponseEntity.badRequest().build();
        }
        Set<AppRole> roles = new HashSet<>();
        roles.add(new AppRole(2, null));

        user.setRoles(roles);
        return ResponseEntity.ok(userService.save(user));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        User u = userService.findById(id).get();
        u.setStatus(false);
        userService.save(u);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
           User u = userService.findByEmail(loginRequest.getEmail()).get();
           Boolean status = u.getStatus();
          if(!status){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Account is blocked!"));
          }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getName(),
                userDetails.getEmail(), userDetails.getPassword(), userDetails.getPhone(), userDetails.getAddress(),
                userDetails.getGender(), userDetails.getStatus(), userDetails.getImage(), userDetails.getRegisterDate(),
                roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signupRequest){
        if(userService.exsitsByEmail(signupRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
        }

        User user = new User(
                signupRequest.getName(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getPhone(),
                signupRequest.getAddress(),
                signupRequest.getGender(),
                signupRequest.getStatus(),
                signupRequest.getImage(),
                signupRequest.getRegisterDate(),
                jwtUtils.doGenerateToken(signupRequest.getEmail())
        );
        Set<AppRole> roles = new HashSet<>();
        roles.add(new AppRole(1, null));
        user.setRoles(roles);
        userService.save(user);
        Cart c = new Cart(0L, 0.0, user.getAddress(), user.getPhone(), user);
        cartService.save(c);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(){ return ResponseEntity.ok().build();}

    @PostMapping("send-email-forget-password-token")
    public ResponseEntity<String> sendToken(@RequestBody String email){
        if(!userService.exsitsByEmail(email)){
            return ResponseEntity.notFound().build();
        }
        User user = userService.findByEmail(email).get();
        String token = user.getToken();
        sendMailToken(email, token, "Reset password");
        return ResponseEntity.ok().build();
    }
    public void sendMailToken(String email, String token, String title){
        String body = "\r\n" + " <h2> click here to reset password </h2>\r\n"
                + " <a href=\"http://localhost:8080/forgot-password/" + token + "\">Reset Password</a>";
        sendMailService.queue(email, title, body);
    }
}
