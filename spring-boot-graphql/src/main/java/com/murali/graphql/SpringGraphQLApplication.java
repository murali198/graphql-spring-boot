package com.murali.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringGraphQLApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGraphQLApplication.class, args);
    }

}
