package com.app.springboot.pcf;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Anish Panthi
 */
@SpringBootApplication
@EnableAdminServer
@EnableEurekaServer
public class SpringBootPcfApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPcfApplication.class, args);
    }

}
