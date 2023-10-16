package tn.bizmatch.authentication.auth;


import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.bizmatch.authentication.SConfig.JwtService;
import tn.bizmatch.authentication.entities.Token;
import tn.bizmatch.authentication.entities.TokenType;
import tn.bizmatch.authentication.entities.User;
import tn.bizmatch.authentication.repositories.TokenRepository;
import tn.bizmatch.authentication.repositories.UserRepository;
import tn.bizmatch.authentication.services.EmailService;


import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final EmailService emailService;

    public Boolean validEmail(String email){
        if(userRepository.validEmail(email)!=null){
            return  false;
        }
        return true;
    }

    public ResponseEntity<?> regiter(RegisterRequest request) {
       /* if (request.getPassword().equals( request.getConfirmpassword())) {*/
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phoneNumber(request.getPhoneNumber())
                    .jobTitle(request.getJobTitle())
                    .companyRole(request.getCompanyRole())
                    .role(request.getRole())
                    .bio(request.getBio())
                    .profilePictureUrl(request.getProfilePictureUrl())
                    .address(request.getAddress())
                    .city(request.getCity())
                    .country(request.getCountry())
                    .postalCode(request.getPostalCode())
                    .isActive(false)
                    .gender(request.getGender())
                    .registrationDate(new Date())
                    .build();
            var saveduser= userRepository.save(user);

            var jwtToken = jwtService.generateToken(user);


            saveUserToken(saveduser, jwtToken);


        Token confirmationToken = new Token(user);

        tokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:9090/api/v1/auth/confirm-account?token="+confirmationToken.getToken());

        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getToken());


        /*return AuthenticationResponse.builder()
                .token(jwtToken)
               // .exception("User created")
                .build();*/
        //return jwtToken;
/*    } else {
        return AuthenticationResponse.builder()
                //.exception("passwords does not match")
                .build();
    }*/
        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }


    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        Token token = tokenRepository.findByToken(confirmationToken).orElse(null);

        if(token != null)
        {
            User user = userRepository.findUserByEmail(token.getUser().getEmail());
            user.setActive(true);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully! now you can connect" );
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        var user=userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken=jwtService.generateToken(user);

        revokeAllUserToken(user);
        saveUserToken(user,jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token= Token.builder().user(user).token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    private void  revokeAllUserToken(User user){
        var validUserTokens= tokenRepository.findAllValidTokenByUser(user.getId());
        if(validUserTokens.isEmpty()){
            return;
        }
        validUserTokens.forEach(t->{
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);

    }






}
