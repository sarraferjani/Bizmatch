package tn.bizmatch.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BizMatchAuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizMatchAuthenticationApplication.class, args);
    }

}
