package tn.bizmatch.authentication.SConfig;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import tn.bizmatch.authentication.repositories.TokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {

        final String authHeader=request.getHeader("Authorization");
        final String jwt;
        // ------------ cheking if we have a jwt token ----------------
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            return;
        }
        // ---------- extract token from the autheader ----------------
        jwt=authHeader.substring(7);
         var storedToken= tokenRepository.findByToken(jwt).orElse(null);
         if(storedToken!=null ){
             storedToken.setRevoked(true);
             storedToken.setExpired(true);
             tokenRepository.save(storedToken);
         }


    }
}
