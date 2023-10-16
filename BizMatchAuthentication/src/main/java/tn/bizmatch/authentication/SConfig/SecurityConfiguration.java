package tn.bizmatch.authentication.SConfig;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import tn.bizmatch.authentication.auth.CorsFilter;
import static tn.bizmatch.authentication.entities.RoleType.*;
import static tn.bizmatch.authentication.entities.Permission.*;


import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity  http) throws Exception {

        http
                .csrf()
                .disable().addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
                .authorizeHttpRequests()
                .antMatchers("/api/v1/auth/**")
                .permitAll()

                .antMatchers("/api/v1/user/**").hasAnyRole(ADMIN.name(), Investor.name(), Collaborator.name(), CRepresentative.name(),Entrepreneur.name())
                .antMatchers(GET,"/api/v1/user/**").hasAuthority(ADMIN_READ.name())
                .antMatchers(POST,"/api/v1/user/**").hasAnyAuthority(ADMIN_POST.name(),Investor_POST.name(),Collaborator_POST.name(),CRepresentative_POST.name(),Entrepreneur_POST.name())
                .antMatchers(POST,"/api/v1/user/**").hasAuthority(ADMIN_PUT.name())

                .antMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
                .antMatchers(GET,"/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
                .antMatchers(POST,"/api/v1/admin/**").hasAuthority(ADMIN_POST.name())

                .antMatchers("/api/v1/auth/logout")
                .permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(
                        (request, response, authentication) -> {
                            SecurityContextHolder.clearContext();

                        }
                                     );


        return http.build();

    }

}
