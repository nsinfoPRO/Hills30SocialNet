package com.Hills30;

import com.Hills30.domain.People;
import com.Hills30.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocialNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> {
            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<People>> typeReference = new TypeReference<List<People>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/data.json");
            try {
                List<People> users = mapper.readValue(inputStream, typeReference);
                System.out.println(inputStream);
                userService.save(users);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        };
    }
}
