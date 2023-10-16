package tn.bizmatch.authentication.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.bizmatch.authentication.Mailing.AppEmailSenderService;
import tn.bizmatch.authentication.SConfig.JwtService;
import tn.bizmatch.authentication.entities.User;
import tn.bizmatch.authentication.repositories.UserRepository;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserRepository userRepository;

    private final AppEmailSenderService appEmailSenderService;


    private  final AuthenticationService authenticationService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody RegisterRequest request)
    {
       return   ResponseEntity.ok(authenticationService.regiter(request));
    }
    @CrossOrigin(origins = "http://localhost:4200")

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return authenticationService.confirmEmail(confirmationToken);
    }

    @GetMapping("/emailvalid/{email}")
    public Boolean validEmail(@PathVariable String email){
        return authenticationService.validEmail(email);
    }

    @GetMapping("/userConnected/{email}")
    public User userconnected(@PathVariable String email){
        return userRepository.findUserByEmail(email);
    }


    @GetMapping("/user/resetPassword/{email}")
    public boolean resetPassword (@PathVariable String email){
        User user =userRepository.validEmail(email);
        if(user!=null) {
            appEmailSenderService.sendMailWithAttachement(user);
            return true;

        }
        return false;
    }
    @GetMapping("/jwtless/emailvalid/{email}")
    public Boolean validEmailsansJWT(@PathVariable String email){
        if(userRepository.findUserByEmail(email)!=null){
            return true;
        }
        return false;

    }

    @PostMapping("/resetPass/{idUser}/{password}")
    public void resetPassword(@PathVariable int idUser,@PathVariable String password) {
        User user = userRepository.findById(idUser).orElse(null);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

    }

}
