package com.simbirsoft.intership;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SimbirsoftIntershipApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimbirsoftIntershipApplication.class, args);
        log.info("test start");
    }

}
