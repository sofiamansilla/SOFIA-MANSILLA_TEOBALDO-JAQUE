package com.dentalClinic.dental;

import com.dentalClinic.dental.service.impl.PatientService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DentalApplication {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(DentalApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DentalApplication.class, args);
        LOGGER.info("\u001B[1mWelcome to our dental clinic APP!! We hope you enjoy it " +
                "and we will be thankful for your advices, Sofia " +
                "and Teobaldo ;)\u001B[0m");

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}



