package tn.bizmatch.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BizMatchDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizMatchDiscoveryServerApplication.class, args);
    }

}
